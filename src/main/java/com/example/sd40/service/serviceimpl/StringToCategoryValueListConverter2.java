package com.example.sd40.service.serviceimpl;


import com.example.sd40.entity.CategoryValue;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StringToCategoryValueListConverter2 implements Converter<String, List<CategoryValue>> {
    @Override
    public List<CategoryValue> convert(String source) {
        List<CategoryValue> categoryValues = new ArrayList<>();
        if (StringUtils.hasText(source)) {
            // Split chuỗi nguồn và tạo danh sách CategoryValue từ các giá trị được phân tách
            String[] values = source.split(",");
            for (String value : values) {
                CategoryValue categoryValue = new CategoryValue();
                categoryValue.setValue(UUID.fromString(value));
                categoryValues.add(categoryValue);
            }
        }
        return categoryValues;
    }
}
