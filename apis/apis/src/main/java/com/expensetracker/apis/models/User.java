package com.expensetracker.apis.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "name", length = 20, nullable = false)
  private String name;

  @Column(name = "email", length = 25, nullable = false, unique = true)
  private String email;

  @Column(name = "password", length = 30, nullable = false)
  private String password;

}
