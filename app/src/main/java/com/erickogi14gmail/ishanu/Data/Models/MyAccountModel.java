package com.erickogi14gmail.ishanu.Data.Models;

import java.io.Serializable;

/**
 * Created by Eric on 11/22/2017.
 */

public class MyAccountModel implements Serializable {
    private String name;
    private String email;
    private String password;
    private String route;
    private String car;
    private String mobilr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getMobilr() {
        return mobilr;
    }

    public void setMobilr(String mobilr) {
        this.mobilr = mobilr;
    }
}
