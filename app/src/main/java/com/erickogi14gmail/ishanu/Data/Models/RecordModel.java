package com.erickogi14gmail.ishanu.Data.Models;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by Eric on 11/23/2017.
 */

public class RecordModel implements Serializable {
    private int record_id;
    private String products;
    private String product_total;
    private String retutns;
    private String returns_total;


    private String paid;
    private String balance;


    private String date;

    private String customer_name;

    private String cash;
    private String mpesa;
    private String cheque;

    private LinkedList<PaymentsModel> paymentsModels;

    public LinkedList<PaymentsModel> getPaymentsModels() {
        return paymentsModels;
    }

    public void setPaymentsModels(LinkedList<PaymentsModel> paymentsModels) {
        this.paymentsModels = paymentsModels;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getMpesa() {
        return mpesa;
    }

    public void setMpesa(String mpesa) {
        this.mpesa = mpesa;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

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
