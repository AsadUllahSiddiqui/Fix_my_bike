package com.example.semester_project_lvl_1;

public class Admin_History_class {
    private String bikeID,workshop,phno,moneySpent,services,date;

    public Admin_History_class(String bikeID, String workshop, String phno,String moneySpent,String services,String date) {
        this.bikeID=bikeID;
        this.workshop=workshop;
        this.phno=phno;
        this.moneySpent=moneySpent;
        this.services=services;
        this.date=date;

    }

    public String getBikeID() {
        return this.bikeID;
    }

    public String getWorkshop() {
        return this.workshop;
    }

    public String getPhno() {
        return this.phno;
    }

    public String getMoneySpent() {
        return this.moneySpent;
    }

    public String getServices() {
        return this.services;
    }

    public String getDate() {
        return this.date;
    }

    public void setBikeID(String bikeid) {
        this.bikeID= bikeid;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

    public void setPhno(String number) {
        this.phno = number;
    }
    public void setMoneySpent(String ms) {
        this.moneySpent= ms;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
