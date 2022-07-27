package com.expensetracker.apis.servicesimpl;

import java.text.MessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
  public ExpenseEntry addExpenseEntry(ExpenseEntryRequest expenseEntryRequest)
      throws ExpenseException {

    log.info(
        MessageFormat.format("addExpenseEntry: expenseEntryRequest = {0}", expenseEntryRequest));

    try {
      ExpenseEntry expenseEntry = new ExpenseEntry();
      expenseEntry.setExpenseCause(expenseEntryRequest.getExpenseCause());
      expenseEntry.setDate(expenseEntryRequest.getDate());
      expenseEntry.setAmount(expenseEntryRequest.getAmount());
      return expenseEntryRepo.save(expenseEntry);

    } catch (Exception e) {
      log.error("Error Occurred While adding expense record", e.getMessage(), e);
      throw new ExpenseException("Error Occurred While adding expense record: ",
          HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

}
