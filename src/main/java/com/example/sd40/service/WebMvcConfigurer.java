package com.example.sd40.service;


import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Service;

@Service
public interface WebMvcConfigurer {
    public void addFormatters(FormatterRegistry registry);
}
