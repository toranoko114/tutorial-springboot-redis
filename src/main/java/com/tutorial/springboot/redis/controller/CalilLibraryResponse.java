package com.tutorial.springboot.redis.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalilLibraryResponse {

  @JsonProperty("systemid")
  private String systemId;

  @JsonProperty("systemname")
  private String systemName;

  @JsonProperty("libkey")
  private String libKey;

  @JsonProperty("libid")
  private String libId;

  @JsonProperty("short")
  private String shortName;

  @JsonProperty("formal")
  private String formalName;

  @JsonProperty("url_pc")
  private String urlPc;

  @JsonProperty("address")
  private String address;

  @JsonProperty("pref")
  private String pref;

  @JsonProperty("city")
  private String city;

  @JsonProperty("post")
  private String post;

  @JsonProperty("tel")
  private String tel;

  @JsonProperty("geocode")
  private String geocode;

  @JsonProperty("category")
  private String category;

  @JsonProperty("image")
  private String image;

  @JsonProperty("distance")
  private Double distance;

}
