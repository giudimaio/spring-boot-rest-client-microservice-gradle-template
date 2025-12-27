package com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.request;

import com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.Dummy;
import jakarta.validation.Valid;

public record PutDummyRequest(@Valid Dummy dummy) {
}
