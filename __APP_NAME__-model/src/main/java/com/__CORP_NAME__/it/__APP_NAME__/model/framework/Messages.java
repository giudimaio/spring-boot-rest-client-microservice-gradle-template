package com.__CORP_NAME__.it.__APP_NAME__.model.framework;

public class Messages {

  public static final String OK = "OK";

  private Messages() {
  }

  public static class Technical {
    public static final String METHOD_BEGINS_TRACE_MESSAGE_TEMPLATE =
        "{}#{} begins, args are: [{}]";
    public static final String METHOD_BEGINS_DEBUG_MESSAGE_TEMPLATE = "{}#{} begins";
    public static final String METHOD_ENDS_DEBUG_MESSAGE_TEMPLATE = "{}#{} ends";
    public static final String METHOD_ENDS_TRACE_MESSAGE_TEMPLATE =
        "{}#{} ends, execution time {} ms, output is: {}";

    private Technical() {
    }

  }

  public static class Error {
    public static final String GENERIC_INTERNAL_ERROR_MESSAGE = "Internal error";
    public static final String GENERIC_BAD_CLIENT_DATA_ERROR_MESSAGE =
        "Missing or malformed data within the request";
    public static final String GENERIC_MISSING_REQUEST_PARAMETERS_ERROR_MESSAGE =
        "Mandatory parameters are missing";
    public static final String GENERIC_INCONSISTENT_CLIENT_DATA_ERROR_MESSAGE =
        "Inconsistent data within the request";
    public static final String API_REQUEST_VALIDATION_ERROR_MESSAGE = "Invalid request";
    public static final String API_REQUEST_PARAMETERS_VALIDATION_ERROR_MESSAGE =
        "Invalid request parameters";
    public static final String UNCAUGHT_EXCEPTION_INTERCEPTED_MESSAGE =
        "Intercepted an uncaught exception";
    public static final String VALIDATION_ERROR_MESSAGE_TEMPLATE =
        "Error while validating {} --> {}";

    private Error() {
    }
  }

  public static class OpenAPI {
    public static final String UUID_OR_NAME_QUERY_FILTER =
        "Filter by either 'uuid' or 'name' (exactly" +
            " one must be provided); 'name' has a minimum length requirement.";

    private OpenAPI() {
    }
  }
}
