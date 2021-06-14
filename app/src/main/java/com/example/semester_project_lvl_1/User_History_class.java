package com.example.semester_project_lvl_1;

public class User_History_class {
    private String bikeID,modelANDcompany,ownerName,phno,costPaid,services,date;

    public User_History_class(String bikeID, String mc,String ownerName, String phno,String cp,String services,String date) {
        this.bikeID=bikeID;
        this.modelANDcompany=mc;
        this.ownerName=ownerName;
        this.phno=phno;
        this.costPaid=cp;
        this.services=services;
        this.date=date;

    }

    public String getBikeID() {
        return this.bikeID;
    }

    public String getModelANDcompany() {
        return this.modelANDcompany;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public String getPhno() {
        return this.phno;
    }

    public String getCostPaid() {
        return this.costPaid;
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

    public void setModelANDcompany(String mc) {
        this.modelANDcompany = mc;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setPhno(String number) {
        this.phno = number;
    }
    public void setCostPaid(String cp) {
        this.costPaid= cp;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
