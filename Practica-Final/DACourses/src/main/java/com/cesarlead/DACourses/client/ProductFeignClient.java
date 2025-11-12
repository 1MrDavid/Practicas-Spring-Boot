package com.cesarlead.DACourses.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "student-service", url = "${student-service.url}")
public interface ProductFeignClient {

  @GetMapping
  List<>
}
