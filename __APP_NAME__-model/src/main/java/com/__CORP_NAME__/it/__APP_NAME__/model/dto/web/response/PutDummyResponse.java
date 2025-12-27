package com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.response;

import com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.Dummy;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class PutDummyResponse extends BaseResponse {
  Dummy updatedRecord;
}
