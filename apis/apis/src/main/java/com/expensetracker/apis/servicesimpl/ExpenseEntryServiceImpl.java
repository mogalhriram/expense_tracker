package com.expensetracker.apis.servicesimpl;

import java.text.MessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.expensetracker.apis.CustomException.ExpenseException;
import com.expensetracker.apis.DAO.ExpenseEntryRepo;
import com.expensetracker.apis.DTOs.ExpenseEntryRequest;
import com.expensetracker.apis.models.ExpenseEntry;
import com.expensetracker.apis.services.ExpenseEntryService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExpenseEntryServiceImpl implements ExpenseEntryService {

  @Autowired
  private ExpenseEntryRepo expenseEntryRepo;

  @Override
  public String addExpenseEntry(ExpenseEntryRequest expenseEntryRequest) throws ExpenseException {

    log.info(
        MessageFormat.format("addExpenseEntry: expenseEntryRequest = {0}", expenseEntryRequest));

    if (expenseEntryRequest == null) {
      log.error("addExpenseEntry: expenseEntryRequest passed null");
      throw new ExpenseException("addExpenseEntry: expenseEntryRequest passed null");
    }

    try {
      ExpenseEntry expenseEntry = new ExpenseEntry();
      expenseEntry.setExpenseCause(expenseEntryRequest.getExpenseCause());
      expenseEntry.setDate(expenseEntryRequest.getDate());
      expenseEntry.setAmount(expenseEntryRequest.getAmount());
      expenseEntryRepo.save(expenseEntry);
      return "Expense Record Added Succesfully";

    } catch (Exception e) {
      log.error("Error Occurred While adding expense record");
      throw new ExpenseException("Error Occurred While adding expense record");
    }
  }

}
