package com.bitwise.onlineshoppingcart.model;

import com.bitwise.onlineshoppingcart.beans.ProductBean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by arpitm on 8/25/2016.
 */
public class Database {
    private HashMap<String,ShoppingCart> productsInCart;

    public HashMap<String, ShoppingCart> getProductsInCart() {
        return productsInCart;
    }

    public void setProductsInCart(HashMap<String, ShoppingCart> productsInCart) {
        this.productsInCart = productsInCart;
    }
}