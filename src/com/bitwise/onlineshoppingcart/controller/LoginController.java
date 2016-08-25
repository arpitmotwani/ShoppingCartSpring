package com.bitwise.onlineshoppingcart.controller;

import com.bitwise.onlineshoppingcart.beans.LoginBean;
import com.bitwise.onlineshoppingcart.beans.ProductBean;
import com.bitwise.onlineshoppingcart.model.Database;
import com.bitwise.onlineshoppingcart.model.ShoppingCart;
import com.bitwise.onlineshoppingcart.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by arpitm on 8/22/2016.
 */
@Controller

public class LoginController {
    @Autowired
    Database database;
    @Autowired
    ShoppingCart cart;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView displayLogin(HttpServletRequest req, Model model) {
        model.addAttribute("loginBean", new LoginBean());
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(ModelMap model, @ModelAttribute("login") LoginBean loginBean, BindingResult result,
                          HttpServletRequest request, HttpSession session) {
        String url;
        UserValidator userValidator = new UserValidator();
        userValidator.validate(loginBean, result);

        if (result.hasErrors()) {
            model.addAttribute("loginBean", loginBean);
            url = "login";
        }

        if ("arpit.motwani@bitwiseglobal.com".equals(loginBean.getUsername()) && "Arpit@123".equals(loginBean.getPassword()))
        {
            session=request.getSession(true);
            String sessionId=session.getId();
            ShoppingCart cart;

            if(request.getSession().getAttribute("cart")==null)
            {
                if(database.getProductsInCart()==null)
                {
                    cart=new ShoppingCart();
                    session.setAttribute("cart",cart);

                    HashMap<String,ShoppingCart> h=new HashMap<>();
                    h.put(loginBean.getUsername(),cart);
                    database.setProductsInCart(h);
                }
                else
                {
                    HashMap<String,ShoppingCart> h=database.getProductsInCart();
                    cart=h.get(loginBean.getUsername());
                    session.setAttribute("cart",cart);
                }
            }
            session.setAttribute("username",loginBean.getUsername());
            session.setAttribute("sessionId",sessionId);
            model.addAttribute("loginBean", loginBean);
            url = "redirect:/productList";
        } else {
            model.addAttribute("loginBean",new LoginBean());
            model.addAttribute("loginError", "Invalid credentials!");
            url = "login";
        }
        return url;
    }

    @RequestMapping(value = "/logout")
    public String logout(Model model, HttpServletRequest request)
    {
        request.getSession().invalidate();
        model.addAttribute("loginBean", new LoginBean());
        return "login";
    }
}