package com.__CORP_NAME__.it.__APP_NAME__.application.service.mock;

import com.__CORP_NAME__.it.__APP_NAME__.application.service.definition.IDummyService;
import com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.Dummy;
import com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.response.GetDummyResponse;
import com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.response.PostDummyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Profile("mock")
public class MockDummyService implements IDummyService {

  public GetDummyResponse getDummy(Long id) {
    return new GetDummyResponse(
        List.of(
            new Dummy(1L, "some dummy")
        )
    );
  }

  public PostDummyResponse postDummy(String dummyName) {

    return new PostDummyResponse(
        new Dummy(
            1L,
            "inserted dummy"
        )
    );
  }

  public PostDummyResponse putDummy(Dummy dummy) {

    return new PostDummyResponse(
        new Dummy(
            1L,
            "updated dummy"
        )
    );
  }
}
