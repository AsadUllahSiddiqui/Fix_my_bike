package com.example.semester_project_lvl_1;

public class order {
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getCompletion_status() {
        return completion_status;
    }

    public void setCompletion_status(String completion_status) {
        this.completion_status = completion_status;
    }

    String date,customer_id,customer_name,customer_address,bike_number,bike_model,bike_company,estimated_price,services_required,workshop_id,completion_status,order_no;

    public order(String date,String completion_status,String customer_id, String customer_name, String customer_address, String bike_number, String bike_model, String bike_company, String estimated_price, String services_required,String workshop_id,String order_no) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_address = customer_address;
        this.bike_number = bike_number;
        this.bike_model = bike_model;
        this.bike_company = bike_company;
        this.estimated_price = estimated_price;
        this.services_required = services_required;
        this.workshop_id = workshop_id;
        this.completion_status = completion_status;
        this.date=date;
        this.order_no=order_no;
    }
    public order() {
        this.customer_id = "";
        this.customer_name = "";
        this.customer_address = "";
        this.bike_number = "";
        this.bike_model = "";
        this.bike_company = "";
        this.estimated_price = "";
        this.services_required = "";
        this.workshop_id = "";
        this.completion_status = "0";
        this.date="";
        this.order_no="";
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getBike_number() {
        return bike_number;
    }

    public void setBike_number(String bike_number) {
        this.bike_number = bike_number;
    }

    public String getBike_model() {
        return bike_model;
    }

    public void setBike_model(String bike_model) {
        this.bike_model = bike_model;
    }

    public String getBike_company() {
        return bike_company;
    }

    public void setBike_company(String bike_company) {
        this.bike_company = bike_company;
    }

    public String getEstimated_price() {
        return estimated_price;
    }

    public void setEstimated_price(String estimated_price) {
        this.estimated_price = estimated_price;
    }

    public String getServices_required() {
        return services_required;
    }

    public void setServices_required(String services_required) {
        this.services_required = services_required;
    }

    public String getWorkshop_id() {
        return workshop_id;
    }

    public void setWorkshop_id(String workshop_id) {
        this.workshop_id = workshop_id;
    }

    public String getData() {
        return date;
    }

    public void setData(String data) {
        this.date = data;
    }
}
