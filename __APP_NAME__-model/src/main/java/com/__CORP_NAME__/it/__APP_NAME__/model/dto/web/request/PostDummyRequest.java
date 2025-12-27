package com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostDummyRequest(@NotNull @NotBlank String dummyName) {
}
