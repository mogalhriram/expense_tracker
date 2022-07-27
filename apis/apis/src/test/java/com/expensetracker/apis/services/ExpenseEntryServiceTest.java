package com.expensetracker.apis.services;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import com.expensetracker.apis.CustomException.ExpenseException;
import com.expensetracker.apis.DAO.ExpenseEntryRepo;
import com.expensetracker.apis.DTOs.ExpenseEntryRequest;
import com.expensetracker.apis.models.ExpenseEntry;
import com.expensetracker.apis.servicesimpl.ExpenseEntryServiceImpl;

class ExpenseEntryServiceTest {

  @InjectMocks
  private ExpenseEntryService expenseEntryService = new ExpenseEntryServiceImpl();

  @Mock
  private ExpenseEntryRepo expenseEntryRepoMock;


  @BeforeEach
  void setUp() {
    expenseEntryRepoMock = Mockito.mock(ExpenseEntryRepo.class);
    ReflectionTestUtils.setField(expenseEntryService, "expenseEntryRepo", expenseEntryRepoMock);
  }


  @Test
  void addExpenseEntryTest() throws ExpenseException {
    ExpenseEntryRequest expenseEntryRequest = new ExpenseEntryRequest();
    expenseEntryRequest.setAmount(100d);
    ExpenseEntry expenseEntry = new ExpenseEntry();
    expenseEntry.setAmount(100d);
    Mockito.when(expenseEntryRepoMock.save(Mockito.any())).thenReturn(expenseEntry);
    ExpenseEntry result = expenseEntryService.addExpenseEntry(expenseEntryRequest);
    Assert.assertEquals(100, result.getAmount(), 0);
  }


  @Test
  void addExpenseEntryExceptionTest() {
    try {
      ExpenseEntryRequest expenseEntryRequest = new ExpenseEntryRequest();
      expenseEntryRequest.setAmount(100d);
      // we will get Type cast exception here
      Mockito.when(expenseEntryRepoMock.save(Mockito.any())).thenReturn(expenseEntryRequest);
      expenseEntryService.addExpenseEntry(expenseEntryRequest);
      Assert.assertTrue(false);
    } catch (Exception e) {
      // test passed
      Assert.assertTrue(true);
    }
  }

}
