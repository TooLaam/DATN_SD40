package com.example.sd40.service.serviceimpl;

import com.example.sd40.entity.Role;
import com.example.sd40.repository.RoleRepository;
import com.example.sd40.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role detail(UUID id) {
        return roleRepository.getById(id);
    }

    @Override
    public List<Role> getAll() {

        return roleRepository.findAll();
    }
}
