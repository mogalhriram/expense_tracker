package com.expensetracker.apis.services;

import java.util.List;
import com.expensetracker.apis.CustomException.ExpenseException;
import com.expensetracker.apis.dto.ExpenseEntryRequest;
import com.expensetracker.apis.models.ExpenseEntry;

public interface ExpenseEntryService {
  ExpenseEntry addExpenseEntry(ExpenseEntryRequest expenseEntryRequest) throws ExpenseException;

  ExpenseEntry updateExpenseEntry(Integer expenseEntryId, ExpenseEntryRequest expenseEntryRequest)
      throws ExpenseException;

  List<ExpenseEntry> getAllExpenseEntries() throws ExpenseException;
}
