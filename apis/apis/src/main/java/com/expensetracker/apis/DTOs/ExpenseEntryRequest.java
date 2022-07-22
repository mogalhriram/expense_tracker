package com.expensetracker.apis.DTOs;

import java.text.MessageFormat;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ExpenseEntryRequest {

  private String expenseCause;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private LocalDate date;

  @NonNull
  private Double amount;

  public String toString() {
    return MessageFormat.format("expenseCause: {0}, date : {1}, amount: {2}", expenseCause, date,
        amount);
  }
}
