package com.bitwise.onlineshoppingcart.model;

import com.bitwise.onlineshoppingcart.beans.ProductBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arpitm on 8/22/2016.
 */
@Component
@Scope("session")
public class ShoppingCart {

    List<ProductBean> cartItems =  new ArrayList<ProductBean>();
    double totalAmount;

    public void addProductToCart(ProductBean product) {
        if(isAvailable(product))
        {
            product.setProductStock(product.getProductStock()-1);
            product.setProductQuantity(product.getProductQuantity()+1);
            cartItems.add(product);
            totalAmount+=product.getProductPrice();
        }
        else
        {
            throw new ProductNotAvailableException("No more items available");
        }
    }

    private boolean isAvailable(ProductBean product) {
        if(product==null)
        {
            return false;
        }
        if(product.getProductStock()-1<0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public List<ProductBean> getCartItems() {
        return cartItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void removeProductFromCart(ProductBean product) {
            for(ProductBean item: cartItems)
            {
                if(item.getProductId().equals(product.getProductId()))
                {
                    product.setProductStock(product.getProductStock()+1);
                    product.setProductQuantity(product.getProductQuantity()-1);
                    cartItems.remove(product);
                    totalAmount-=product.getProductPrice();
                    break;
                }
            }
    }
}