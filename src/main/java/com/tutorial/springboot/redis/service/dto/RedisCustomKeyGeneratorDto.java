package com.tutorial.springboot.redis.service.dto;

import com.tutorial.springboot.redis.controller.CalilLibrarySearchParameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RedisCustomKeyGeneratorDto {
  private String className;
  private String methodName;
  private CalilLibrarySearchParameter params;
}
