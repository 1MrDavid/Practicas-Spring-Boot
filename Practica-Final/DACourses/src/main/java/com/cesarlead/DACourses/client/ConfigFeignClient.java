package com.cesarlead.DACourses.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.cesarlead.DACourses.dto.ConfigDTO;

@FeignClient(name = "config-service", url = "${student-service.url}")
public interface ConfigFeignClient {

  @GetMapping("/config/app-info")
  ConfigDTO getAppconfig();
}
