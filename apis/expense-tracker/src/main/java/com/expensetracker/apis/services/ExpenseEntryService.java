package com.expensetracker.apis.services;

import com.expensetracker.apis.CustomException.ExpenseException;
import com.expensetracker.apis.dto.ExpenseEntryRequest;
import com.expensetracker.apis.models.ExpenseEntry;

import java.util.List;

public interface ExpenseEntryService {
  ExpenseEntry addExpenseEntry(ExpenseEntryRequest expenseEntryRequest) throws ExpenseException;

  ExpenseEntry updateExpenseEntry(Integer expenseEntryId, ExpenseEntryRequest expenseEntryRequest)
      throws ExpenseException;
  List<ExpenseEntry> GetAllExpenseEntries() throws ExpenseException;
}
