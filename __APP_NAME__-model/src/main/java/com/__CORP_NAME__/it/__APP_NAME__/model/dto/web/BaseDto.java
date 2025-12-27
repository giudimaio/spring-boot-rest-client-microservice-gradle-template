package com.__CORP_NAME__.it.__APP_NAME__.model.dto.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class BaseDto {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String toString() {
    try {
      return objectMapper.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      return super.toString();
    }
  }
}
