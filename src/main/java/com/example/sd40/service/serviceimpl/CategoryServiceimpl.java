package com.example.sd40.service.serviceimpl;

import com.example.sd40.entity.Category;
import com.example.sd40.repository.CategoryResponsitory;
import com.example.sd40.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceimpl implements CategoryService {
    @Autowired
    CategoryResponsitory responsitory;
    @Override
    public ArrayList<Category> getAll() {
        return (ArrayList<Category>) responsitory.findAll();
    }

    @Override
    public void save(Category category) {
         responsitory.saveAndFlush(category);
    }

    @Override
    public void delete(UUID id) {
         responsitory.deleteById(id);
    }

    @Override
    public void update(UUID id, Category category) {
           Category a = getOne(id);
           a.setName(category.getName());
        a.setStatus(category.getStatus());
           responsitory.flush();
    }

    @Override
    public Category getOne(UUID id) {
        Optional<Category> categoryOptional = responsitory.findById(id);
        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        } else {
           return categoryOptional.get();
        }
    }



}
