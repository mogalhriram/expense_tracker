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
import com.expensetracker.apis.models.ExpenseEntry;
import com.expensetracker.apis.services.ExpenseEntryService;
import com.expensetracker.apis.utils.ResponseHandler;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;

@RestController
@RequestMapping("/api")
public class ExpenseEntryController {

  @Autowired
  private ExpenseEntryService expenseEntryService;

  // TODO later we will add user id as path variable /users/{userid}/expenses
  @PostMapping("/expenses/")
  @ApiOperation(value = "Add Expense record",
      notes = "TODO later we will add user id as path variable /users/{userid}/expenses")
  @ApiResponses(value = {@ApiResponse(code = 201, message = "Expense record Added Succesfully"),
      @ApiResponse(code = 400, message = "Bad Request. Response body would contain more details"),
      @ApiResponse(code = 401, message = "Unauthorized."),
      @ApiResponse(code = 422,
          message = "Unprocessable Entity. Response body would contain more details"),
      @ApiResponse(code = 500,
          message = "Internal Server Error. Response body would contain more details")})
  ResponseEntity<Object> addExpenseEntry(
      @Valid @RequestBody ExpenseEntryRequest expenseEntryRequest) throws ExpenseException {

    return ResponseHandler.generateResponse("Expense Record Added Succesfully!", HttpStatus.CREATED,
        expenseEntryService.addExpenseEntry(expenseEntryRequest));

  }
}
