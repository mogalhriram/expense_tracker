package com.expensetracker.apis.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.expensetracker.apis.models.ExpenseEntry;

@Service
public interface ExpenseEntryRepo extends JpaRepository<ExpenseEntry, Integer> {

}
