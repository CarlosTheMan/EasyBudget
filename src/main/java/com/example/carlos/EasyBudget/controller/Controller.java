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

    @Autowired
    Database repoUser;

    @Autowired
    DatabaseExpenses repoExpense;

    public String useridSave;
    public UserData userData;

    //Home page
    @RequestMapping("/Home")
    public ModelAndView homePage(){
        return new ModelAndView("homepage");
    }

    //Validate User
    @RequestMapping("/Login")
    public ModelAndView validateUser(UserData userinfo){
        ServiceLayer service = new ServiceLayer();
        ModelAndView mvcDash = new ModelAndView("adduserpage");
        if (service.validateCredentials(userinfo, repoUser)) {
            useridSave = userinfo.getUserid();
            userData = userinfo;
            mvcDash.setViewName("dashboardpage");

            //Add objects
            mvcDash.addObject("infoObj", service);
            mvcDash.addObject("userObj", userinfo);
            mvcDash.addObject("expenseObj", service.showExpenses(useridSave, repoExpense));
            return mvcDash;
        }
        return mvcDash;
    }

    @RequestMapping("/Dashboard")
    public ModelAndView loadDash(){
        ServiceLayer service = new ServiceLayer();
        ModelAndView mvcDash = new ModelAndView("dashboardpage");

        //Load objects
        mvcDash.addObject("infoObj", service);
        mvcDash.addObject("userObj", userData);
        mvcDash.addObject("expenseObj", service.showExpenses(useridSave, repoExpense));
        return mvcDash;
    }

    //Add Users to DB
    @RequestMapping("/AddUsers")
    public ModelAndView addUser(UserData addUserData){
        ServiceLayer serviceLayer = new ServiceLayer();
        if (!serviceLayer.checkIfUserExists(addUserData, repoUser)){
            repoUser.save(addUserData);
            return new ModelAndView("useraddedpage");
        }
        return new ModelAndView("homepage");
    }

    //Add Expenses
    @RequestMapping("/AddExpense")
    public ModelAndView addExpense(UserDataExpenses addUserDataExpenses){
        ServiceLayer expense = new ServiceLayer();
        if (useridSave == null){
            return new ModelAndView("homepage");
        }
        else {
            addUserDataExpenses.setUserid(useridSave);
            addUserDataExpenses.setDate(expense.dateOut(Integer.parseInt(addUserDataExpenses.getDate())));
            repoExpense.save(addUserDataExpenses);
        }
        return new ModelAndView("expensepage");
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