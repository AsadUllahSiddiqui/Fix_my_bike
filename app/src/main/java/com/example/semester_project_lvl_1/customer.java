package com.example.semester_project_lvl_1;

public class customer {
    String first_name,last_name,img,address,phone,bio,gender,id,email,lat,lng;

    public customer(String id,String first_name, String last_name, String img, String address, String phone, String bio, String gender,String email,String lat,String lng) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.img = img;
        this.address = address;
        this.phone = phone;
        this.bio = bio;
        this.gender = gender;
        this.id=id;
        this.email=email;
        this.lat=lat;
        this.lng=lng;
    }
    public customer() {
        this.first_name = "";
        this.last_name = "";
        this.img = "";
        this.address = "";
        this.phone = "";
        this.bio = "";
        this.gender = "";
        this.id="";
        this.email="";
        this.lat="";
        this.lng="";
    }
    public customer(String email,String id) {
        this.first_name = "";
        this.last_name = "";
        this.img = "";
        this.address = "";
        this.phone = "";
        this.bio = "";
        this.gender = "";
        this.id=id;
        this.email=email;
    }
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
