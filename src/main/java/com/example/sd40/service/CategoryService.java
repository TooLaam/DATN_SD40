package com.example.sd40.service;

import com.example.sd40.entity.Category;

import java.util.ArrayList;
import java.util.UUID;

public interface CategoryService {

    ArrayList<Category> getAll();

    void save(Category category);

    void delete(UUID id);

    void update(UUID id, Category category);

    Category getOne(UUID id);
}
