package com.__CORP_NAME__.it.__APP_NAME__.application.service;

import com.__CORP_NAME__.it.__APP_NAME__.application.mapper.mapstruct.DummyMapper;
import com.__CORP_NAME__.it.__APP_NAME__.application.service.definition.IDummyService;
import com.__CORP_NAME__.it.__APP_NAME__.data.repository.DummyRepository;
import com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.Dummy;
import com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.response.GetDummyResponse;
import com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.response.PostDummyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Profile("!mock")
public class DummyService implements IDummyService {

  private final DummyRepository dummyRepository;
  private final DummyMapper dummyMapper;

  public GetDummyResponse getDummy(Long id) {
    return new GetDummyResponse(
        dummyRepository.findById(id).stream()
            .map(dummyMapper::dummyEntityToDummyDto)
            .toList()
    );
  }

  public PostDummyResponse postDummy(String dummyName) {

    final com.__CORP_NAME__.it.__APP_NAME__.model.entity.business.Dummy insertedDummy =
        dummyRepository.save(
            new com.__CORP_NAME__.it.__APP_NAME__.model.entity.business.Dummy(
                dummyName));

    return new PostDummyResponse(
        new Dummy(
            insertedDummy.getId(),
            insertedDummy.getName()
        )
    );
  }

  public PostDummyResponse putDummy(Dummy dummy) {

    final com.__CORP_NAME__.it.__APP_NAME__.model.entity.business.Dummy insertedDummy =
        dummyRepository.save(
            dummyMapper.dummyDtoToDummyEntity(dummy));

    return new PostDummyResponse(
        new Dummy(
            insertedDummy.getId(),
            insertedDummy.getName()
        )
    );
  }
}
