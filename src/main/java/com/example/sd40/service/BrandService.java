package com.example.sd40.service;

import com.example.sd40.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;


public interface BrandService {

    void add(Brand brand);
    void delete(UUID id);
    void update(UUID id, Brand brand);
    List<Brand> getAll();
    Page<Brand> getAllWithPagination(Pageable pageable);
    Brand getOne(UUID id);

}
