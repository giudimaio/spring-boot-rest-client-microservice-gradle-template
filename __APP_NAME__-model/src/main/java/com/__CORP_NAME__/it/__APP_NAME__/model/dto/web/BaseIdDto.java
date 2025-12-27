package com.__CORP_NAME__.it.__APP_NAME__.model.dto.web;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class BaseIdDto extends BaseDto {
  private final @NotNull Long id;

  public BaseIdDto(@NotNull Long id) {
    this.id = id;
  }
}
