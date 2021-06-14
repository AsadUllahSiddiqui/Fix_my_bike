package com.example.semester_project_lvl_1;

public class workshop_profile_veriable {
     static workshop myData;
    workshop_profile_veriable(){
    }
    public workshop_profile_veriable(workshop myData) {
        this.myData = myData;
    }

    public static workshop getMyData() {
        return myData;
    }

    public void setMyData(workshop myData) {
        this.myData = myData;
    }
}
