package com.example.sd40.service;

import com.example.sd40.entity.CategoryDetail;

import java.util.List;
import java.util.UUID;

public interface CategoryDetailService {
    void add(CategoryDetail categoryDetail);
    void delete(UUID id);
    void update(UUID id, CategoryDetail categoryDetail);
    List<CategoryDetail> getAll();
   CategoryDetail getOne(UUID id);
}
