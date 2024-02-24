package com.example.sd40.service.serviceimpl;


import com.example.sd40.entity.Brand;
import com.example.sd40.repository.BrandResponsitory;
import com.example.sd40.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BrandServiceimpl implements BrandService {
    @Autowired
    BrandResponsitory responsitory;
    @Override
    public void add(Brand brand) {
         responsitory.saveAndFlush(brand);
    }

    @Override
    public void delete(UUID id) {
            responsitory.deleteById(id);
    }

    @Override
    public void update(UUID id, Brand brand) {
        Brand bra = getOne(id);
        bra.setName(brand.getName());
        bra.setImage(brand.getImage());
        bra.setStaTus(brand.getStaTus());
        responsitory.flush();

    }

    @Override
    public List<Brand> getAll() {
        return responsitory.findAll();
    }

    @Override
    public Page<Brand> getAllWithPagination(Pageable pageable) {
        return responsitory.findAll(pageable);
    }

    @Override
    public Brand getOne(UUID id) {
        return responsitory.findById(id).get();
    }
}
