package com.cesarlead.DACourses.client;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.cesarlead.DACourses.config.AppConstant;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
@Slf4j
public class FeignTraceIdInterceptor implements RequestInterceptor {

  @Override
  public void apply(RequestTemplate template) {

    String traceId = MDC.get(AppConstant.TRACE_ID_MDC_KEY);

    log.info("FeignTraceIdInterceptor ejecutándose...");
    log.info("Trace ID desde MDC: {}", traceId);
    log.info("URL destino: {}", template.url());
    log.info("Headers actuales: {}", template.headers());

    if (traceId != null && !traceId.isEmpty()) {
      if (template.headers().get(AppConstant.TRACE_ID_HEADER) == null) {
        template.header(AppConstant.TRACE_ID_HEADER, traceId);
      }
    }
  }
}
