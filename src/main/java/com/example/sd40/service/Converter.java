package com.example.sd40.service;

import com.example.sd40.entity.CategoryValue;

import java.util.List;

public interface Converter {
    List<CategoryValue> convert(String source);
}
