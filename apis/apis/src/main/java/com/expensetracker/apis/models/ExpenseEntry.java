package com.expensetracker.apis.models;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "expense_entries")
@Getter
@Setter
public class ExpenseEntry {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "expenseCause", length = 100)
  private String expenseCause;

  @Column(name = "date")
  private LocalDate date;

  @Column(name = "amount", nullable = false)
  private Double amount;

  // @ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
  // private Integer user;
}
