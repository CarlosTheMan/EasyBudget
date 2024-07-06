package com.example.carlos.EasyBudget.controller;

import com.example.carlos.EasyBudget.persistence.mongodb.Database;
import com.example.carlos.EasyBudget.persistence.model.UserData;
import com.example.carlos.EasyBudget.persistence.model.UserDataExpenses;
import com.example.carlos.EasyBudget.persistence.mongodb.DatabaseExpenses;
import com.example.carlos.EasyBudget.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Controller {

    //Initialize DB for user information
    @Autowired
    Database repoUser;

    //Initialize DB for user expenses
    @Autowired
    DatabaseExpenses repoExpense;

    //Public variables
    public String useridSave;
    public UserData userData;

    //Login page
    @RequestMapping("/Home")
    public ModelAndView homePage(){
        return new ModelAndView("homepage");
    }

    //Login and validate User
    @RequestMapping("/Login")
    public ModelAndView validateUser(UserData userinfo){
        ServiceLayer service = new ServiceLayer();
        ModelAndView mvcDash = new ModelAndView("loginerrorpage");
        //Validate user credentials before logging in
        if (service.validateCredentials(userinfo, repoUser)) {
            useridSave = userinfo.getUserid();
            userData = userinfo;

            //Load initial objects
            mvcDash.addObject("infoObj", service);
            mvcDash.addObject("userObj", userinfo);
            mvcDash.addObject("expenseObj", service.showExpenses(useridSave, repoExpense));
            mvcDash.setViewName("dashboardpage");
            return mvcDash;
        }
        return mvcDash;
    }

    //Dashboard page
    @RequestMapping("/Dashboard")
    public ModelAndView loadDash(){
        //If user is not logged in - return to homepage
        if (useridSave == null) {
            return new ModelAndView("homepage");
        }
        else {
            ServiceLayer service = new ServiceLayer();
            ModelAndView mvcDash = new ModelAndView("dashboardpage");

            //Load objects to page
            mvcDash.addObject("infoObj", service);
            mvcDash.addObject("userObj", userData);
            mvcDash.addObject("expenseObj", service.showExpenses(useridSave, repoExpense));
            return mvcDash;
        }
    }

    //Add Users to DB
    @RequestMapping("/AddUsers")
    public ModelAndView addUser(UserData addUserData){
        ServiceLayer serviceLayer = new ServiceLayer();
        //Add User if username doesn't exist and if user did not provide null
        if (addUserData.getUserid() != null){
            if (!serviceLayer.checkIfUserExists(addUserData, repoUser)) {
                repoUser.save(addUserData);
                return new ModelAndView("useraddedpage");
            }
        }
        return new ModelAndView("adduserpage");
    }

    //Add Expenses
    @RequestMapping("/AddExpense")
    public ModelAndView addExpense(UserDataExpenses addUserDataExpenses){
        ServiceLayer date = new ServiceLayer();
        if (useridSave != null){
            //Initialize userID and date for the userExpense repo
            addUserDataExpenses.setUserid(useridSave);
            addUserDataExpenses.setDate(date.dateOut(Integer.parseInt(addUserDataExpenses.getDate())));

            //Save object
            repoExpense.save(addUserDataExpenses);
            return new ModelAndView("expensepage");
        }
        return new ModelAndView("homepage");
    }
    // Delete expense
    @RequestMapping("/DeleteExpense")
    public ModelAndView deleteExpense(String id) {
        repoExpense.deleteById(id);
        return new ModelAndView("expensedeleted");
    }

    //Easy Budget Login
    //--Validate User (throw error if user not found - request to create a user) -- check
    //--Add User feature for above (checks to see if user already exists) -- check

    //Budget screen
    //--Add Expense (add an expense to the DB for a specific user) -- check
    //--Retrieve Expense -- check
    //--Show 7 day calendar view
    //--Set spending bucket limits
    //--Show spending buckets

    //--Later feature--
    //Track spending over month
    //
}
