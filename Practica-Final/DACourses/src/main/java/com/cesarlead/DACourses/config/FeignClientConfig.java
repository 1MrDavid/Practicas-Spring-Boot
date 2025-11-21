package com.cesarlead.DACourses.config;

import com.cesarlead.DACourses.client.CustomErrorDecoder;
import com.cesarlead.DACourses.client.FeignTraceIdInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.ErrorDecoder;

@Configuration
public class FeignClientConfig {

  @Bean
  public ErrorDecoder errorDecoder() {
    return new CustomErrorDecoder();
  }

  @Bean
  public FeignTraceIdInterceptor feignTraceIdInterceptor() {
    return new FeignTraceIdInterceptor();
  }
}
