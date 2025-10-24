package com.expensetracker.service;

import com.expensetracker.dto.ExpenseDTO;
import com.expensetracker.entity.ExpenseEntity;
import com.expensetracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;


    public ExpenseDTO add (ExpenseDTO expenseDTO){
       ExpenseEntity expenseEntity = ExpenseEntity.builder()
               .id(expenseDTO.getId())
               .date(LocalDate.now())
               .description(expenseDTO.getDescription())
               .amount(expenseDTO.getAmount())
               .category(expenseDTO.getCategory())
               .build();
       expenseRepository.save(expenseEntity);

       return ExpenseDTO.builder()
               .id(expenseEntity.getId())
               .date(LocalDate.now())
               .description(expenseEntity.getDescription())
               .amount(expenseEntity.getAmount())
               .category(expenseEntity.getCategory())
               .build();
    }

    public ExpenseDTO update (String id , ExpenseDTO expenseDTO){
        ExpenseEntity expenseEntity = expenseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"id not found"));
        expenseEntity.setId(expenseDTO.getId());
        expenseEntity.setAmount(expenseDTO.getAmount());
        expenseEntity.setDescription(expenseDTO.getDescription());
        expenseEntity.setCategory(expenseDTO.getCategory());

        expenseRepository.save(expenseEntity);

        return ExpenseDTO.builder()
                .id(expenseEntity.getId())
                .description(expenseEntity.getDescription())
                .amount(expenseEntity.getAmount())
                .category(expenseEntity.getCategory())
                .build();
    }

    public String delete(String id){
        ExpenseEntity expenseEntity = expenseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"id not found"));
        expenseRepository.delete(expenseEntity);
        return "Delete Successfully";
    }

    public List<ExpenseDTO> getAll(){
        List<ExpenseEntity> entityList = expenseRepository.findAll();
        List<ExpenseDTO>  dtoList = new ArrayList<>();

        for(ExpenseEntity entity : entityList){
            ExpenseDTO expenseDTO = ExpenseDTO.builder()
                    .id(entity.getId())
                    .date(entity.getDate())
                    .amount(entity.getAmount())
                    .description(entity.getDescription())
                    .category(entity.getCategory())
                    .build();
            dtoList.add(expenseDTO);
        }
        return dtoList;
    }

    public double getSummary(){
        List<ExpenseEntity> entityList = expenseRepository.findAll();
        double total = 0.0;
        for(ExpenseEntity expenseEntity : entityList){
            total += expenseEntity.getAmount();
        }
        return total;
    }

    public double getMonthly(int month){
        List<ExpenseEntity> entityList = expenseRepository.findAll();
        double total = 0.0;
        for(ExpenseEntity expenseEntity : entityList){
            if(expenseEntity.getDate() != null && expenseEntity.getDate().getMonthValue() == month) {
                total += expenseEntity.getAmount();
            }
        }
        return total;
    }
}

