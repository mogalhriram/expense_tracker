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
  void addExpenseEntryTestWithNullRequest() {
    // passing null expenseEntryRequest
    try {
      expenseEntryService.addExpenseEntry(null);
      Assert.assertTrue(false);
    } catch (Exception e) {
      Assert.assertEquals("addExpenseEntry: expenseEntryRequest passed null", e.getMessage());
    }
  }

  @Test
  void addExpenseEntryTest() throws ExpenseException {
    ExpenseEntryRequest expenseEntryRequest = new ExpenseEntryRequest();
    expenseEntryRequest.setAmount(100d);
    Mockito.when(expenseEntryRepoMock.save(Mockito.any())).thenReturn(null);
    String result = expenseEntryService.addExpenseEntry(expenseEntryRequest);
    Assert.assertEquals("Expense Record Added Succesfully", result);
  }


  // @Test
  // @Ignore("Test to be fixed")
  // void addExpenseEntryExceptionTest() {
  // try {
  // ExpenseEntryRequest expenseEntryRequest = new ExpenseEntryRequest();
  // expenseEntryRequest.setAmount(100d);
  // expenseEntryService.addExpenseEntry(expenseEntryRequest);
  // Mockito.when(expenseEntryRepoMock.save(Mockito.any())).thenThrow(new RuntimeException());
  // assertTrue(false);
  // } catch (Exception e) {
  // // test passed
  // assertTrue(true);
  // }
  // }

}
