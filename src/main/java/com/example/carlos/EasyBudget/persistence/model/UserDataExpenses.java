package com.example.carlos.EasyBudget.persistence.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Expenses")
public class UserDataExpenses {

    private String userid;
    private String category;
    private String expense;
    private String date;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
