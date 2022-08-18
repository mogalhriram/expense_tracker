package com.expensetracker.apis.services.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class BaseSetup {

  public static final String EXPENSE_ENTRIES_FILE = "ExpenseEntries.json";



  protected <T> T getInstanceFromJson(String strFileName, Class<T> classOfT, TypeReference<T> type)
      throws IOException {
    final ObjectMapper objectMapper =
        Jackson2ObjectMapperBuilder.json().serializationInclusion(JsonInclude.Include.NON_NULL)
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // ISODate
            .build();
    ClassPathResource cpr = new ClassPathResource(strFileName);
    T instance;
    if (type != null) {
      instance = objectMapper.readValue(new InputStreamReader(cpr.getInputStream()), type);
    } else {
      instance = objectMapper.readValue(new InputStreamReader(cpr.getInputStream()), classOfT);
    }
    return instance;
  }
}
