package com.cesarlead.DACourses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DaCoursesApplication {

  public static void main(String[] args) {
    SpringApplication.run(DaCoursesApplication.class, args);
  }

}
