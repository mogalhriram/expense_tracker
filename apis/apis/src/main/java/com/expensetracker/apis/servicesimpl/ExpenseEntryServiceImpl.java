package com.expensetracker.apis.servicesimpl;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.expensetracker.apis.CustomException.ExpenseException;
import com.expensetracker.apis.DAO.ExpenseEntryRepo;
import com.expensetracker.apis.DTOs.ExpenseEntryRequest;
import com.expensetracker.apis.models.ExpenseEntry;
import com.expensetracker.apis.services.ExpenseEntryService;
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

    if (Objects.isNull(expenseEntryRequest) || Objects.isNull(expenseEntryRequest.getAmount())) {
      log.error("Amount Can not be null!");
      throw new ExpenseException("Amount Can not be null!", HttpStatus.BAD_REQUEST,
          expenseEntryRequest);
    }

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

  @Override
  public ExpenseEntry updateExpenseEntry(Integer expenseEntryId,
      ExpenseEntryRequest expenseEntryRequest) throws ExpenseException {
    Optional<ExpenseEntry> expenseEntryRecord = expenseEntryRepo.findById(expenseEntryId);
    if (!expenseEntryRecord.isPresent() || Objects.isNull(expenseEntryRecord.get())) {
      log.error("Expense Record Not found for expenseEntryId : {}", expenseEntryId);
      throw new ExpenseException("Expense Record Not found for expenseEntryId: " + expenseEntryId,
          HttpStatus.BAD_REQUEST, expenseEntryId);
    }

    try {
      ExpenseEntry expenseEntry = expenseEntryRecord.get();
      if (!Objects.isNull(expenseEntryRequest.getExpenseCause()))
        expenseEntry.setExpenseCause(expenseEntryRequest.getExpenseCause());
      if (!Objects.isNull(expenseEntryRequest.getAmount()))
        expenseEntry.setAmount(expenseEntryRequest.getAmount());
      if (!Objects.isNull(expenseEntryRequest.getDate()))
        expenseEntry.setDate(expenseEntryRequest.getDate());
      return expenseEntryRepo.save(expenseEntry);
    } catch (Exception e) {
      log.error("Error Occured While Updating Expense Record : {}", e.getMessage(), e);
      throw new ExpenseException("Error Occured While Updating Expense Record :" + e.getMessage(),
          HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }

}
