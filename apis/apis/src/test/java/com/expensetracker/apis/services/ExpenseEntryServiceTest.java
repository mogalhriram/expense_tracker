package com.expensetracker.apis.services;

import com.expensetracker.apis.services.utils.BaseSetup;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import com.expensetracker.apis.CustomException.ExpenseException;
import com.expensetracker.apis.dao.ExpenseEntryRepo;
import com.expensetracker.apis.dto.ExpenseEntryRequest;
import com.expensetracker.apis.models.ExpenseEntry;
import com.expensetracker.apis.servicesimpl.ExpenseEntryServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ExpenseEntryServiceTest extends BaseSetup {

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

  @Test
  void fetchAllExpenseTest() throws IOException, ExpenseException {
     Mockito.when(expenseEntryRepoMock.findAll()).thenReturn(getAllExpenseEntries());
     List<ExpenseEntry> expenseEntries = expenseEntryService.GetAllExpenseEntries();
     Assert.assertNotNull(expenseEntries);
     Assert.assertEquals(1,expenseEntries.get(0).getId().intValue());
     Assert.assertEquals(2,expenseEntries.get(1).getId().intValue());
  }

  private List<ExpenseEntry> getAllExpenseEntries() throws IOException {
    return getInstanceFromJson(EXPENSE_ENTRIES_FILE,null ,new TypeReference<List<ExpenseEntry>>(){});
  }

}
