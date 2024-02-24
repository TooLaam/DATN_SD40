package com.example.sd40.repository;


import com.example.sd40.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ColorResponsitory extends JpaRepository<Color, UUID> {
}
