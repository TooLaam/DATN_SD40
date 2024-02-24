package com.example.sd40.repository;


import com.example.sd40.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BrandResponsitory extends JpaRepository<Brand,UUID> {
}
