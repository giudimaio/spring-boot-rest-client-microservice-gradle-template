package com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.response;

import com.__CORP_NAME__.it.__APP_NAME__.model.dto.web.Dummy;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter(onMethod_ = @SuppressFBWarnings({"EI_EXPOSE_REP"}))
@AllArgsConstructor(onConstructor_ = @SuppressFBWarnings({"EI_EXPOSE_REP2"}))
public class GetDummyResponse extends BaseResponse {
  private final List<Dummy> dummies;
}
