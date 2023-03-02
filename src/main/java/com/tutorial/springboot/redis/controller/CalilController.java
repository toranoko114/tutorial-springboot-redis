package com.tutorial.springboot.redis.controller;

import com.tutorial.springboot.redis.service.CalilService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calil")
@Slf4j
@RequiredArgsConstructor
public class CalilController {

  private final CalilService service;
  private final ModelMapper modelMapper;

  @GetMapping("/libraries")
  public List<CalilLibraryResponse> fetchLibraries(
      @Validated @ModelAttribute CalilLibrarySearchParameter params,
      BindingResult result
  ) {
    if (result.hasErrors()) {
      result.getFieldErrors()
          .forEach(e -> log.error("{} : {}", e.getField(), e.getDefaultMessage()));
      return null;
    }
    return List.of(
        this.modelMapper.map(this.service.fetchLibraries(params), CalilLibraryResponse[].class)
    );
  }

}
