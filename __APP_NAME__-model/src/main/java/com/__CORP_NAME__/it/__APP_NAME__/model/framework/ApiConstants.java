package com.__CORP_NAME__.it.__APP_NAME__.model.framework;

public class ApiConstants {

  private ApiConstants() {
  }

  public static class Common {

    public static final String EMPTY_PATH = "";
    public static final String ID_PATH_PARAM = "/{id}";

    private Common() {
    }
  }

  public static class DummyController {

    public static final String BASE_PATH = "/dummies";

    private DummyController() {
    }
  }

  public static class OptionController {

    public static final String BASE_PATH = "/options";

    public static final String REQUEST_CONTEXTS = "/request-contexts";
    public static final String REQUESTOR_TYPES = "/requestor-types";
    public static final String REQUEST_TOPICS = "/request-topics";

    private OptionController() {
    }
  }
}
