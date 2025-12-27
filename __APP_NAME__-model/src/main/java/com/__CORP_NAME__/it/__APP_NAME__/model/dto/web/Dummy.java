package com.__CORP_NAME__.it.__APP_NAME__.model.dto.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class Dummy extends BaseIdDto {
    @NotNull()
    @NotBlank()
    private final String name;

    public Dummy(Long id, String name) {
        super(id);
        this.name = name;
    }
}
