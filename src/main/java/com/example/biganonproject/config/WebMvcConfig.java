package com.example.biganonproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public void addViewResolver(ViewControllerRegistry registry){
        registry.addViewController("/myNav").setViewName("navigator");
        registry.addViewController("/ListSameTypes").setViewName("ListSameTypes");
        registry.addViewController("/uploadForm").setViewName("uploadForm");
    }
}
