package com.__CORP_NAME__.it.__APP_NAME__.application.service.definition;

import com.__CORP_NAME__.it.__APP_NAME__.model.framework.ApplicationMessageId;

import java.util.Locale;

public interface IApplicationMessageService {

  String getMessage(ApplicationMessageId messageKey, Locale locale);
}
