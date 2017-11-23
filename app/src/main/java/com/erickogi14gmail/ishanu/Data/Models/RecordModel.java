package com.erickogi14gmail.ishanu.Data.Models;

/**
 * Created by Eric on 11/23/2017.
 */

public class RecordModel {
    private int record_id;
    private String products;
    private String product_total;
    private String retutns;
    private String returns_total;

    private String paid;
    private String balance;

    private String date;

    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getProduct_total() {
        return product_total;
    }

    public void setProduct_total(String product_total) {
        this.product_total = product_total;
    }

    public String getRetutns() {
        return retutns;
    }

    public void setRetutns(String retutns) {
        this.retutns = retutns;
    }

    public String getReturns_total() {
        return returns_total;
    }

    public void setReturns_total(String returns_total) {
        this.returns_total = returns_total;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
