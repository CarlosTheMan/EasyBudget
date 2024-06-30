package com.example.carlos.EasyBudget.persistence.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class UserData {

    private String userid;
    private String userpassword;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    @Override
    public String toString() {
        return "UserData:" + "Username=" + userid + ", Password=" + userpassword;
    }

}