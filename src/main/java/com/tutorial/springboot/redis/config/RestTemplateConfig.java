package com.tutorial.springboot.redis.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

  private final RestTemplateBuilder restTemplateBuilder;

  /**
   * 公開API カーリル用のRestTemplate定義.
   *
   * @return カーリル用のRestTemplate
   * @see 'https://calil.jp/doc/api_ref.html'
   */
  @Bean("calilRestTemplate")
  public RestTemplate calilRestTemplate() {
    return restTemplateBuilder.build();
  }

}
