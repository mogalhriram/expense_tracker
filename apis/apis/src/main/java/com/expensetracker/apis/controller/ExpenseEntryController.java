package com.expensetracker.apis.controller;

import javax.validation.Valid;
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
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class ExpenseEntryController {

  @Autowired
  private ExpenseEntryService expenseEntryService;

  // TODO later we will add user id as path variable /users/{userid}/expenses
  @PostMapping("/expenses/")
  @ApiOperation(value = "Add Expense record",
      notes = "TODO later we will add user id as path variable /users/{userid}/expenses")
  ResponseEntity<String> addExpenseEntry(
      @Valid @RequestBody ExpenseEntryRequest expenseEntryRequest) throws ExpenseException {

    String response = expenseEntryService.addExpenseEntry(expenseEntryRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);

  }
}
