package com.__CORP_NAME__.it.__APP_NAME__.model.framework;

import com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.response.BaseResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class ApiDocumentation {
  private ApiDocumentation() {
  }

  @Target({ElementType.METHOD, ElementType.TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @ApiResponses(
      @ApiResponse(
          responseCode = HttpCode.INTERNAL_SERVER_ERROR,
          description = Description.INTERNAL_SERVER_ERROR,
          content = @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(
                  implementation = BaseResponse.class
              )
          )
      )
  )
  public @interface InternalServerErrorResponse {
  }

  @Target({ElementType.METHOD, ElementType.TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @ApiResponses(
      @ApiResponse(
          responseCode = HttpCode.BAD_REQUEST,
          description = Description.BAD_REQUEST,
          content = @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(
                  implementation = BaseResponse.class
              )
          )
      )
  )
  public @interface BadRequestResponse {
  }

  @Target({ElementType.METHOD, ElementType.TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @ApiResponses(
      @ApiResponse(
          responseCode = HttpCode.NOT_FOUND,
          description = Description.RECORD_NOT_FOUND,
          content = @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(
                  implementation = BaseResponse.class
              )
          )
      )
  )
  public @interface NotFoundResponse {
  }

  public static class HttpCode {
    public static final String SUCCESS = "200";
    public static final String ACCEPTED = "202";
    public static final String NO_CONTENT = "204";
    public static final String BAD_REQUEST = "400";
    public static final String UNAUTHORIZED = "401";
    public static final String FORBIDDEN = "403";
    public static final String NOT_FOUND = "404";
    public static final String INTERNAL_SERVER_ERROR = "500";
    public static final String CONFLICT = "409";

    private HttpCode() {
    }
  }

  public static class Description {
    public static final String EXECUTED_CORRECTLY = "Executed correctly";
    public static final String BAD_REQUEST = "Bad or missing data within the request";
    public static final String CONFLICT = "Conflict";
    public static final String BAD_CREDENTIALS = "Bad credentials";
    public static final String SHOULD_UPDATE_PASSWORD =
        "Successful with reserve, password update required";
    public static final String RECORD_NOT_FOUND = "Record not found";
    public static final String INTERNAL_SERVER_ERROR =
        "Internal server error or web service failure";
    public static final String TOO_MANY_ATTEMPTS = "Reached too many attempts";
    public static final String WRONG_AUTHENTICATION = "Wrong authentication";
    public static final String FORBIDDEN = "Forbidden";
    public static final String NOT_AUTHORIZED = "User unauthorized";

    private Description() {
    }
  }
}
