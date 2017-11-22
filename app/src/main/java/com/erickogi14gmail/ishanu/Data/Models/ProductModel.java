package com.erickogi14gmail.ishanu.Data.Models;

import java.io.Serializable;

/**
 * Created by Eric on 11/21/2017.
 */

public class ProductModel implements Serializable {

    private String product_id;
    private String product_name;
    private double product_load_quantity;
    private double product_sale_quantity;
    private double product_price;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getProduct_load_quantity() {
        return product_load_quantity;
    }

    public void setProduct_load_quantity(double product_load_quantity) {
        this.product_load_quantity = product_load_quantity;
    }

    public double getProduct_sale_quantity() {
        return product_sale_quantity;
    }

    public void setProduct_sale_quantity(double product_sale_quantity) {
        this.product_sale_quantity = product_sale_quantity;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }
}
