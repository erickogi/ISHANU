package com.erickogi14gmail.ishanu.Data.Models;

import java.io.Serializable;

/**
 * Created by Eric on 11/30/2017.
 */

public class CustomerModel implements Serializable {
    private int cusomer_id;
    private String customer_name;
    private String customer_location;


    public int getCusomer_id() {
        return cusomer_id;
    }

    public void setCusomer_id(int cusomer_id) {
        this.cusomer_id = cusomer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_location() {
        return customer_location;
    }

    public void setCustomer_location(String customer_location) {
        this.customer_location = customer_location;
    }
}
