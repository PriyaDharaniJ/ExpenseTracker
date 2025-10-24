package com.expensetracker.controller;


import com.expensetracker.dto.ExpenseDTO;
import com.expensetracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("/add")
    public ExpenseDTO add(@RequestBody ExpenseDTO expenseDTO){
        return expenseService.add(expenseDTO);
    }

    @PutMapping("/update/{id}")
    public ExpenseDTO update( @PathVariable String id, @RequestBody ExpenseDTO expenseDTO){
        return expenseService.update(id, expenseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        return expenseService.delete(id);
    }

    @GetMapping("/getAll")
    public List<ExpenseDTO> getAll(){
        return expenseService.getAll();
    }
    @GetMapping("/getSummary")
    public String getSummary(){
        double total = expenseService.getSummary();
        return "total expenses : $" + total;
    }
    @GetMapping("/getMonthlyTotal")
    public String getMonthlyTotal(@PathVariable int month){
        double total = expenseService.getMonthly(month);
        return "Monthly total for " + month +" is $  :"+ total;

    }
}
