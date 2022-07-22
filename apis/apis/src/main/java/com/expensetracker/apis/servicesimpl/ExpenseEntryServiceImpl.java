package com.expensetracker.apis.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.expensetracker.apis.CustomException.ExpenseException;
import com.expensetracker.apis.DAO.ExpenseEntryRepo;
import com.expensetracker.apis.DTOs.ExpenseEntryRequest;
import com.expensetracker.apis.models.ExpenseEntry;
import com.expensetracker.apis.services.ExpenseEntryService;

@Service
public class ExpenseEntryServiceImpl implements ExpenseEntryService {

  @Autowired
  private ExpenseEntryRepo expenseEntryRepo;

  @Override
  public String addExpenseEntry(ExpenseEntryRequest expenseEntryRequest) throws ExpenseException {
    try {
      ExpenseEntry expenseEntry = new ExpenseEntry();
      expenseEntry.setExpenseCause(expenseEntryRequest.getExpenseCause());
      expenseEntry.setDate(expenseEntryRequest.getDate());
      expenseEntry.setAmount(expenseEntryRequest.getAmount());
      expenseEntryRepo.save(expenseEntry);
      return "Expense Record Added Succesfully";

    } catch (Exception e) {
      throw new ExpenseException("Error Occurred While adding expense record");
    }
  }

}
