package com.__CORP_NAME__.it.__APP_NAME__.application.mapper.mapstruct;

import com.__CORP_NAME__.it.__APP_NAME__.model.entity.business.Dummy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DummyMapper {

    com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.Dummy dummyEntityToDummyDto(Dummy dummyEntity);

    default Dummy dummyDtoToDummyEntity(
            com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.Dummy dummy) {
        return new Dummy(dummy.getName());
    }
}
