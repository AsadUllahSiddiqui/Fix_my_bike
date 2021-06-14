package com.example.semester_project_lvl_1;

public class bike {
    String company,model,number,customer_id;

    public bike(String company, String model, String number, String customer_id) {
        this.company = company;
        this.model = model;
        this.number = number;
        this.customer_id = customer_id;
    }
    public bike(String company, String model, String number) {
        this.company = company;
        this.model = model;
        this.number = number;
        this.customer_id = "";
    }
    public bike() {
        this.company = "";
        this.model = "";
        this.number = "";
        this.customer_id = "";
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
}
