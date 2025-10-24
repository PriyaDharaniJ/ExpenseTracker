package com.expensetracker.repository;

import com.expensetracker.entity.ExpenseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExpenseRepository extends MongoRepository<ExpenseEntity , String> {
}
