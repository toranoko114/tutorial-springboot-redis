package com.tutorial.springboot.redis.infrastructure;

import com.tutorial.springboot.redis.controller.CalilLibrarySearchParameter;
import com.tutorial.springboot.redis.service.dto.CalilLibraryDto;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class CalilApiClient {

  @Qualifier("calilRestTemplate")
  private final RestTemplate calilRestTemplate;

  @Value("${calil-api.url}")
  private String url;

  @Value("${calil-api.appKey}")
  private String appKey;

  public ResponseEntity<List<CalilLibraryDto>> fetchLibraries(CalilLibrarySearchParameter params) {
    var uri = UriComponentsBuilder.fromHttpUrl(this.url)
        .queryParam("appkey", this.appKey)
        .queryParamIfPresent("pref", Optional.ofNullable(params.getPref()))
        .queryParamIfPresent("city", Optional.ofNullable(params.getCity()))
        .queryParamIfPresent("systemid", Optional.ofNullable(params.getSystemid()))
        .queryParamIfPresent("geocode", Optional.ofNullable(params.getGeocode()))
        .queryParamIfPresent("format", Optional.ofNullable(params.getFormat()))
        .queryParamIfPresent("callback", Optional.ofNullable(params.getCallback()))
        .queryParamIfPresent("limit", Optional.ofNullable(params.getLimit()))
        .build()
        .toUri();
    return this.calilRestTemplate.exchange(uri, HttpMethod.GET, HttpEntity.EMPTY,
        new ParameterizedTypeReference<>() {
        });
  }
}
