package com.__CORP_NAME__.it.__APP_NAME__.application.service.definition;

import com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.Dummy;
import com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.response.GetDummyResponse;
import com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.response.PostDummyResponse;

public interface IDummyService {

  GetDummyResponse getDummy(Long id);

  PostDummyResponse postDummy(String dummyName);

  PostDummyResponse putDummy(Dummy dummy);
}
