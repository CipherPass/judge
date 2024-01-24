package com.example.leetcode_clone.config.webconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Value("${debug}")
  private boolean debug;

  @Value("${origin}")
  private String origin;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**")
        .addResourceLocations("classpath:/static/")
        .resourceChain(true)
        .addResolver(new ReactPathResourceResolver());
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    if (debug) {
      registry.addMapping("/**")
          .allowedOrigins("*");
    } else {
      // Restrict CORS for non-debug mode
      registry.addMapping("/**").allowedOrigins(origin); // Adjust the allowed origin as needed
    }
  }
}
