package com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.response;

import com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.BaseDto;
import com.__CORP_NAME__.it.__APP_NAME__.model.framework.ErrorCode;
import com.__CORP_NAME__.it.__APP_NAME__.model.framework.Messages;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BaseResponse extends BaseDto {
  private final String message;
  private final ErrorCode errorCode;

  public BaseResponse() {
    this.message = Messages.OK;
    this.errorCode = null;
  }

  public BaseResponse(String message) {
    this.message = message;
    this.errorCode = null;
  }
}
