package com.__CORP_NAME__.it.__APP_NAME__.model.framework;

public class CacheConstants {

  private CacheConstants() {
  }

  public static class Maps {
    public static final String DUMMIES = "dummies";

    private Maps() {
    }
  }

  public static class Keys {

    public static final String ID_NAME_KEY =
        "#p0.uuid != null ? 'uuid:' + #p0.uuid : 'name:' + #p0.name";

    private Keys() {
    }
  }
}
