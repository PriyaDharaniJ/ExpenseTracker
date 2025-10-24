package com.expensetracker.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Document(collection = "expenseDetails")
@Builder
public class ExpenseEntity {

    @Id
    private String id;
    private LocalDate date;
    private String description;
    private double amount;
    private String category;
}
