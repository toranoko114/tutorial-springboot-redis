package com.tutorial.springboot.redis.controller;

import io.netty.util.internal.StringUtil;
import jakarta.validation.constraints.AssertTrue;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CalilLibrarySearchParameter {

  /**
   * 都道府県コード
   */
  private String pref;

  private String systemid;

  private String geocode;

  private String city;

  private String format = "json";

  /**
   * JSONとして応答する場合はcallbackに空白を指定
   */
  private String callback = "";

  private Integer limit;

  /**
   * いずれかは指定必須のパラメータについてチェック
   *
   * @return 判定結果
   */
  @AssertTrue(message = "either parameter must be specified.")
  public boolean isAtLeastOneRequired() {
    return !(StringUtil.isNullOrEmpty(this.pref)
        && StringUtil.isNullOrEmpty(this.systemid)
        && StringUtil.isNullOrEmpty(this.geocode));
  }

}
