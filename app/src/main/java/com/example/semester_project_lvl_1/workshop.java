package com.example.semester_project_lvl_1;

public class workshop {
    private String workshop_name,workshop_address,workshop_number,pic,owner_name,bio,id,email;
    private double lat,lng;

    public workshop(String email,String workshop_name, String workshop_address, String workshop_number, String pic, String owner_name, String bio,String id,double lat,double lng) {
        this.workshop_name = workshop_name;
        this.workshop_address = workshop_address;
        this.workshop_number = workshop_number;
        this.pic = pic;
        this.owner_name = owner_name;
        this.bio = bio;
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.email = email;
    }
    public workshop(String workshop_name, String workshop_address, String workshop_number)
    {
        this.workshop_name = workshop_name;
        this.workshop_address = workshop_address;
        this.workshop_number = workshop_number;
        this.pic = "";
        this.owner_name = "";
        this.bio = "";
        this.id = "";
        this.lat = 0;
        this.lng = 0;
    }
    public workshop(String email, String id)
    {
        this.email = email;
        this.workshop_name = "";
        this.workshop_address = "";
        this.workshop_number =  "";
        this.pic = "";
        this.owner_name = "";
        this.bio = "";
        this.id = id;
        this.lat = 0;
        this.lng = 0;
    }

    public workshop() {
        this.workshop_name = "";
        this.workshop_address = "";
        this.workshop_number = "";
        this.pic = "";
        this.owner_name = "";
        this.bio = "";
        this.id = "";
        this.lat = 0;
        this.lng = 0;
    }

    public String getWorkshop_name() {
        return workshop_name;
    }

    public String getWorkshop_address() {
        return workshop_address;
    }

    public String getWorkshop_number() {
        return workshop_number;
    }

    public void setWorkshop_name(String name) {
        workshop_name = name;
    }

    public void setWorkshop_address(String address) {
        workshop_address = address;
    }

    public void setWorkshop_number(String number) {
        workshop_number = number;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
