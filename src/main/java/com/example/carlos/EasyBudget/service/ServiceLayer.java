package com.example.carlos.EasyBudget.service;

import com.example.carlos.EasyBudget.persistence.model.UserData;
import com.example.carlos.EasyBudget.persistence.model.UserDataExpenses;
import com.example.carlos.EasyBudget.persistence.mongodb.Database;
import com.example.carlos.EasyBudget.persistence.mongodb.DatabaseExpenses;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;


public class ServiceLayer {

    public boolean checkIfUserExists(UserData userData, Database repo) {
        for (int i = 0; i < repo.findAll().size(); i++) {
            if (repo.findAll().get(i).getUserid().equals(userData.getUserid())) {
                return true;
            }
        }
        return false;
    }

    public boolean validateCredentials(UserData userData, Database repo) {
        for (int i = 0; i < repo.findAll().size(); i++) {
            if (repo.findAll().get(i).getUserid().equals(userData.getUserid())) {
                if (repo.findAll().get(i).getUserpassword().equals(userData.getUserpassword())) {
                    return true;
                }
            }
        }
        return false;
    }

    public String dateOut(int d) {
        LocalDate date = LocalDate.now();
        int month = Integer.parseInt(date.with(WeekFields.ISO.getFirstDayOfWeek()).toString().substring(5, 7));
        int day = Integer.parseInt(date.with(WeekFields.ISO.getFirstDayOfWeek()).toString().substring(8));
        int year = Integer.parseInt(date.with(WeekFields.ISO.getFirstDayOfWeek()).toString().substring(0, 4));

        return "Date: " + year + "-" + month + "-" + (day + d);
    }

    public List<UserDataExpenses> showExpenses(String userID, DatabaseExpenses repo) {
        List<UserDataExpenses> expenseList = repo.findAll();
        List<UserDataExpenses> expenses = new ArrayList<>();
        for (UserDataExpenses expense : expenseList) {
            if (expense.getUserid().equals(userID)) {
                expenses.add(expense);
            }
        }
        return expenses;
    }
}
