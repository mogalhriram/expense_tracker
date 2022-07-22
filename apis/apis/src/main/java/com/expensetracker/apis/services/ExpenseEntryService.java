package com.expensetracker.apis.services;

import com.expensetracker.apis.CustomException.ExpenseException;
import com.expensetracker.apis.DTOs.ExpenseEntryRequest;

public interface ExpenseEntryService {
  String addExpenseEntry(ExpenseEntryRequest expenseEntryRequest) throws ExpenseException;
}
