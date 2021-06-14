package com.example.semester_project_lvl_1;

public class my_customers {
     private String customer_id,model,services,address,picture_uri;

    public my_customers(String customer_id, String model, String services, String address, String picture_uri) {
        this.customer_id = customer_id;
        this.model = model;
        this.services = services;
        this.address = address;
        this.picture_uri = picture_uri;
    }
    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicture_uri() {
        return picture_uri;
    }

    public void setPicture_uri(String picture_uri) {
        this.picture_uri = picture_uri;
    }
}

