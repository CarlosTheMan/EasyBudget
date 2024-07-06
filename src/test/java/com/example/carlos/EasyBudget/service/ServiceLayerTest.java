package com.example.carlos.EasyBudget.service;

import com.example.carlos.EasyBudget.EasyBudgetApplication;
import com.example.carlos.EasyBudget.persistence.model.UserData;
import com.example.carlos.EasyBudget.persistence.mongodb.Database;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@DataMongoTest
@ExtendWith(SpringExtension.class)
class ServiceLayerTest {

    @Autowired
    private Database database;

    @Test
    void checkIfUserExists() {
        UserData test = new UserData();
        test.setUserid("testuser");
        ServiceLayer check = new ServiceLayer();
        assertFalse(check.checkIfUserExists(test,database));
    }

    @Test
    void validateCredentials() {
    }

    @Test
    void dateOut() {
    }

    @Test
    void showExpenses() {
    }
}