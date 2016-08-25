package com.bitwise.onlineshoppingcart.controller;

import com.bitwise.onlineshoppingcart.beans.LoginBean;
import com.bitwise.onlineshoppingcart.beans.ProductBean;
import com.bitwise.onlineshoppingcart.model.Database;
import com.bitwise.onlineshoppingcart.model.ProductList;
import com.bitwise.onlineshoppingcart.model.ProductNotAvailableException;
import com.bitwise.onlineshoppingcart.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by arpitm on 8/11/2016.
 */

@Controller
public class OnlineShoppingCartController {
    @Autowired
    ProductList productList;
    @Autowired
    ProductBean product;
    @Autowired
    Database database;
//    @Autowired
//    ShoppingCart cart;
//    @Autowired
//    Order order;
    @RequestMapping(value="/productList")

    public String displayProducts(HttpServletRequest request, Model model)
    {
        HttpSession session=request.getSession(false);
        if(session!=null && "arpit.motwani@bitwiseglobal.com".equals(session.getAttribute("username")) && session.getAttribute("sessionId").equals(session.getId()))
        {
            model.addAttribute("product",product);
            model.addAttribute("productList",productList);
            return "productList";
        }
        else
        {
            System.out.println("INVALID SESSION");
            model.addAttribute("loginBean",new LoginBean());
            model.addAttribute("loginErrorDueToSession","Please Login to continue");
            return "login";
        }
        }



    @RequestMapping(value = "/displayCart")
    public String displayCart(ModelMap model, HttpServletRequest request, HttpSession session)
    {
        if(session!=null && "arpit.motwani@bitwiseglobal.com".equals(session.getAttribute("username")) && session.getAttribute("sessionId").equals(session.getId()))
        {
            ShoppingCart cart=(ShoppingCart)session.getAttribute("cart");
            if(cart==null)
            {
                return "zeroItemPlacedError";
            }
            Set<ProductBean> productsInCart=new HashSet<>();
            for(ProductBean p: cart.getCartItems())
            {
                productsInCart.add(p);
            }
            model.addAttribute("cart",productsInCart);
            return "displayCart";
        }
        else
        {
            System.out.println("INVALID SESSION");
            model.addAttribute("loginBean",new LoginBean());
            model.addAttribute("loginErrorDueToSession","Please Login to continue");
            return "login";
        }
    }


    @RequestMapping(value = "/placeOrder")
    public String placeOrder(ModelMap model, HttpServletRequest request, HttpSession session)
    {
        if(session!=null && "arpit.motwani@bitwiseglobal.com".equals(session.getAttribute("username")) && session.getAttribute("sessionId").equals(session.getId()))
        {
            ShoppingCart cart=(ShoppingCart)session.getAttribute("cart");
            if(cart.getCartItems().size()==0)
            {
                return "zeroItemPlacedError";
            }
            Set<ProductBean> productsInCart= new HashSet<>();
            for(ProductBean p: cart.getCartItems())
            {
                productsInCart.add(p);
            }
            model.addAttribute("cart",productsInCart);
            model.addAttribute("totalAmount",cart.getTotalAmount());

            List<Integer> qty=new ArrayList<>();
            for(ProductBean p:productsInCart)
            {
                qty.add(p.getProductQuantity());
            }
            model.addAttribute("quantity",qty);

            int x;
            x=0;
            List<ProductBean> placedOrderItems=cart.getCartItems();
            while(x<placedOrderItems.size())
            {
                cart.getCartItems().get(x).setProductQuantity(0);
                x++;
            }
            cart=new ShoppingCart();

            HashMap<String,ShoppingCart> h=new HashMap<>();
            h.put((String)session.getAttribute("username"),cart);
            database.setProductsInCart(h);
            session.setAttribute("cart",cart);

            return "placeOrder";
        }
        else
        {
            System.out.println("INVALID SESSION");
            model.addAttribute("loginBean",new LoginBean());
            model.addAttribute("loginErrorDueToSession","Please Login to continue");
            return "login";
        }
    }

    @RequestMapping(value = "/addProduct")
    public String addNewProduct(@ModelAttribute("product") ProductBean productBean, Model model, HttpServletRequest request, HttpSession session)
    {
        if(session!=null && "arpit.motwani@bitwiseglobal.com".equals(session.getAttribute("username")) && session.getAttribute("sessionId").equals(session.getId()))
        {
            ShoppingCart cart=(ShoppingCart)session.getAttribute("cart");
            product=productList.getProductByProductId(productBean.getProductId());
            try
            {
                cart.addProductToCart(product);
                HashMap<String,ShoppingCart> h=database.getProductsInCart();
                h.put((String)session.getAttribute("username"),cart);
                session.setAttribute("cart",cart);
            }catch(ProductNotAvailableException p)
            {
                model.addAttribute("exceptionMessage",p.getMessage());
                return "exception";
            }
            return "redirect:/productList";
        }
        else
        {
            System.out.println("INVALID SESSION");
            model.addAttribute("loginBean",new LoginBean());
            model.addAttribute("loginErrorDueToSession","Please Login to continue");
            return "login";
        }
    }

    @RequestMapping(value = "/removeProduct")
    public String removeProduct(@ModelAttribute("product") ProductBean productBean, Model model, HttpSession session)
    {
        if(session!=null && "arpit.motwani@bitwiseglobal.com".equals(session.getAttribute("username")) && session.getAttribute("sessionId").equals(session.getId())) {
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            product=productList.getProductByProductId(productBean.getProductId());
            cart.removeProductFromCart(product);
            model.addAttribute("cart",cart);
            return "redirect:/displayCart";
        }
        else
        {
            System.out.println("INVALID SESSION");
            model.addAttribute("loginBean",new LoginBean());
            model.addAttribute("loginErrorDueToSession","Please Login to continue");
            return "login";
        }
    }

    @ExceptionHandler(ProductNotAvailableException.class)
    public ModelAndView handleCustomException(ProductNotAvailableException p) {
        return new ModelAndView("exception","message",p.getMessage());

    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {

        ModelAndView model = new ModelAndView("exception");
        model.addObject("message", "Something went wrong!");
        return model;
    }
}