package com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.response;

import com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.Dummy;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
@AllArgsConstructor
public class PostDummyResponse extends BaseResponse {
  private final Dummy insertedRecord;
}
