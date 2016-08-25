package com.bitwise.onlineshoppingcart.model;

import com.bitwise.onlineshoppingcart.beans.ProductBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by arpitm on 8/22/2016.
 */
@Component
@Scope("session")
public class ProductList {
    private static List<ProductBean> products = new ArrayList<>();

    public ProductList() {
        products.add(new ProductBean("1111", "Shoes", 3500.00,2,0));
        products.add(new ProductBean("2222", "Football", 5000.00, 6,0));
        products.add(new ProductBean("3333", "Racket", 2000.00, 5,0));
        products.add(new ProductBean("4444", "Shirt", 1200.00, 1,0));
        products.add(new ProductBean("5555", "Geans", 2000.00, 7,0));
        products.add(new ProductBean("6666", "Watch", 3000.00, 2,0));
        products.add(new ProductBean("7777", "Floaters", 1500.00, 1,0));
        products.add(new ProductBean("8888", "Shampoo", 1000.00, 6,0));
        products.add(new ProductBean("9999", "Phone", 25000.00,4,0));
        products.add(new ProductBean("1010", "Powerbank", 2000.00, 2,0));
    }

    public static List<ProductBean> getProducts() {
        return products;
    }

    public static void setProducts(List<ProductBean> products) {
        ProductList.products = products;
    }

    public ProductBean getProductByProductId(String productId)
    {
        for(ProductBean product:products)
        {
            if(product.getProductId().equals(productId))
            {
                return product;
            }
        }
        return null;
    }
}
