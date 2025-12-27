package com.__CORP_NAME__.it.__APP_NAME__.application.controller;

import com.__CORP_NAME__.it.__APP_NAME__.application.service.definition.IDummyService;
import com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.request.PostDummyRequest;
import com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.request.PutDummyRequest;
import com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.response.GetDummyResponse;
import com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.response.PostDummyResponse;
import com.__CORP_NAME__.it.__APP_NAME__.model.framework.ApiConstants;
import com.__CORP_NAME__.it.__APP_NAME__.model.framework.ApiDocumentation;
import com.__CORP_NAME__.it.__APP_NAME__.model.framework.CacheConstants;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
//@SecurityRequirement(name = "basicAuth") // enable if necessary
@RequestMapping(ApiConstants.DummyController.BASE_PATH)
@RequiredArgsConstructor
public class DummyController {

  private final IDummyService dummyService;

  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = ApiDocumentation.HttpCode.SUCCESS,
              description = ApiDocumentation.Description.EXECUTED_CORRECTLY,
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(
                      implementation = GetDummyResponse.class
                  )
              )
          )
      }
  )
  @ApiDocumentation.InternalServerErrorResponse
  @ApiDocumentation.BadRequestResponse
  @ApiDocumentation.NotFoundResponse
  @GetMapping(ApiConstants.Common.EMPTY_PATH + ApiConstants.Common.ID_PATH_PARAM)
  @Cacheable(
          cacheNames = CacheConstants.Maps.DUMMIES,
          key = CacheConstants.Keys.ID_NAME_KEY)
  public GetDummyResponse getDummy(
      @PathVariable Long id) {
    return dummyService.getDummy(id);
  }

  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = ApiDocumentation.HttpCode.SUCCESS,
              description = ApiDocumentation.Description.EXECUTED_CORRECTLY,
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(
                      implementation = PostDummyResponse.class
                  )
              )
          )
      }
  )
  @ApiDocumentation.InternalServerErrorResponse
  @ApiDocumentation.BadRequestResponse
  @PostMapping(value = ApiConstants.Common.EMPTY_PATH, consumes = MediaType.APPLICATION_JSON_VALUE)
  public PostDummyResponse postDummy(@RequestBody @Valid PostDummyRequest request) {
    return dummyService.postDummy(request.dummyName());
  }

  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = ApiDocumentation.HttpCode.SUCCESS,
              description = ApiDocumentation.Description.EXECUTED_CORRECTLY,
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(
                      implementation = PostDummyResponse.class
                  )
              )
          )
      }
  )
  @ApiDocumentation.InternalServerErrorResponse
  @ApiDocumentation.BadRequestResponse
  @PutMapping(value = ApiConstants.Common.EMPTY_PATH, consumes = MediaType.APPLICATION_JSON_VALUE)
  public PostDummyResponse putDummy(@RequestBody PutDummyRequest request) {
    return dummyService.putDummy(request.dummy());
  }
}
