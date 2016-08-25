package com.bitwise.onlineshoppingcart.model;

import com.bitwise.onlineshoppingcart.beans.ProductBean;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by arpitm on 8/22/2016.
 */
public class Order {
    private String pid;
    private String pname;
    private double price;
    private int quantity;
    Set<ProductBean> orderList;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    public Set<ProductBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(Set<ProductBean> orderList) {
        this.orderList = orderList;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void makeOrder(List<ProductBean> cartItems) {
        orderList = new HashSet<ProductBean>(cartItems);
        for(ProductBean product : orderList){
            System.out.println(Collections.frequency(cartItems,product));
            this.setQuantity(Collections.frequency(cartItems,product));
        }
    }
}
