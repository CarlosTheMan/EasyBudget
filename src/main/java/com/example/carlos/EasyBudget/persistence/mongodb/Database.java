package com.example.carlos.EasyBudget.persistence.mongodb;

import com.example.carlos.EasyBudget.persistence.model.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Database extends MongoRepository<UserData, String> {
}
