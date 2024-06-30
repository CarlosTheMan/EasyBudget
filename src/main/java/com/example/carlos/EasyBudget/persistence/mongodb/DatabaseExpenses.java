package com.example.carlos.EasyBudget.persistence.mongodb;

import com.example.carlos.EasyBudget.persistence.model.UserDataExpenses;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatabaseExpenses extends MongoRepository<UserDataExpenses, String> {
}
