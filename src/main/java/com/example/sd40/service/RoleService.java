package com.example.sd40.service;

import com.example.sd40.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface RoleService {
    Role detail(UUID id);
    List<Role> getAll();
}
