package com.example.semester_project_lvl_1;
public class user_profile_veriable {
    static customer myData;
    user_profile_veriable(){
    }
    public user_profile_veriable(customer myData) {
        this.myData = myData;
    }

    public static customer getMyData() {
        return myData;
    }

    public void setMyData(customer myData) {
        this.myData = myData;
    }
}
