package com.expensetracker.apis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.expensetracker.apis.CustomException.ExpenseException;
import com.expensetracker.apis.DTOs.ExpenseEntryRequest;
import com.expensetracker.apis.services.ExpenseEntryService;

@RestController
@RequestMapping("/")
public class ExpenseEntryController {

  @Autowired
  private ExpenseEntryService expenseEntryService;

  // TODO later we add user id as path variable /users/{userid}/expenses
  @PostMapping("apis/expenses/")
  ResponseEntity<String> addExpenseEntry(@RequestBody ExpenseEntryRequest expenseEntryRequest)
      throws ExpenseException {

    String response = expenseEntryService.addExpenseEntry(expenseEntryRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);

  }
}
