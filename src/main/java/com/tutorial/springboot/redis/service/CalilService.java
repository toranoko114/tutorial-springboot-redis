package com.tutorial.springboot.redis.service;

import com.tutorial.springboot.redis.controller.CalilLibrarySearchParameter;
import com.tutorial.springboot.redis.infrastructure.CalilApiClient;
import com.tutorial.springboot.redis.service.dto.CalilLibraryDto;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalilService {

  private final CalilApiClient apiClient;

  @Cacheable(cacheNames = "tutorialRedisCache")
  public List<CalilLibraryDto> fetchLibraries(CalilLibrarySearchParameter params) {
    return Optional.of(this.apiClient.fetchLibraries(params))
        .map(HttpEntity::getBody)
        .orElseThrow(() -> new RuntimeException("図書館の取得に失敗しました。"));
  }
}
