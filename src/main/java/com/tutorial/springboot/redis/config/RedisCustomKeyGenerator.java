package com.tutorial.springboot.redis.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorial.springboot.redis.controller.CalilLibrarySearchParameter;
import com.tutorial.springboot.redis.service.dto.RedisCustomKeyGeneratorDto;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.cache.interceptor.KeyGenerator;

public class RedisCustomKeyGenerator implements KeyGenerator {

  @Override
  public Object generate(Object target, Method method, Object... params) {

    var customParams = Arrays.stream(params)
        .filter(o -> o instanceof CalilLibrarySearchParameter)
        .map(o -> (CalilLibrarySearchParameter) o)
        .findFirst()
        .orElse(null);

    var redisCustomKeyGeneratorDto = RedisCustomKeyGeneratorDto.builder()
        .className(target.getClass().getSimpleName())
        .methodName(method.getName())
        .params(customParams)
        .build();

    String json;
    ObjectMapper mapper = new ObjectMapper();
    try {
      json = mapper.writeValueAsString(redisCustomKeyGeneratorDto);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return DigestUtils.md5Hex(json);
  }
}
