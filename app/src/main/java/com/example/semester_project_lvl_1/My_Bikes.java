package com.example.semester_project_lvl_1;

public class My_Bikes {
    private String Company,Model,Number,customer_id;

    public My_Bikes(String company, String model, String number,String customer_id) {
        Company = company;
        Model = model;
        Number = number;
        Number = customer_id;
    }
    public My_Bikes(String company, String model, String number) {
        Company = company;
        Model = model;
        Number = number;
        customer_id="";
    }
    public My_Bikes( ) {
        Company = "";
        Model = "";
        Number = "";
        customer_id="";
    }

    public String getCompany() {
        return Company;
    }

    public String getModel() {
        return Model;
    }

    public String getNumber() {
        return Number;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public void setModel(String model) {
        Model = model;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
}

