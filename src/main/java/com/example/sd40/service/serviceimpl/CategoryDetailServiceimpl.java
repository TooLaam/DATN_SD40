package com.example.sd40.service.serviceimpl;


import com.example.sd40.entity.CategoryDetail;
import com.example.sd40.repository.CategoryDetailResponsitory;
import com.example.sd40.service.CategoryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryDetailServiceimpl implements CategoryDetailService {

    @Autowired
    CategoryDetailResponsitory responsitory;
    @Override
    public void add(CategoryDetail categoryDetail) {
        responsitory.saveAndFlush(categoryDetail);
    }

    @Override
    public void delete(UUID id) {
            responsitory.deleteById(id);
    }

    @Override
    public void update(UUID id, CategoryDetail categoryDetail) {
            CategoryDetail detail = getOne(id);
            detail.setQuanTity(categoryDetail.getQuanTity());
        detail.setProduct(categoryDetail.getProduct());
        detail.setCategory(categoryDetail.getCategory());
        responsitory.flush();
    }

    @Override
    public List<CategoryDetail> getAll() {
        return responsitory.findAll();
    }

    @Override
    public CategoryDetail getOne(UUID id) {
        return responsitory.findById(id).get();
    }
}
