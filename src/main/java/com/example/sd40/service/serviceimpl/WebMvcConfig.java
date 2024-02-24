package com.example.sd40.service.serviceimpl;

import com.example.sd40.service.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public StringToCategoryValueListConverter2 stringToCategoryValueListConverter() {
        return new StringToCategoryValueListConverter2();
    }
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToCategoryValueListConverter());
    }
}
