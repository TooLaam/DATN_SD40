package com.example.sd40.service;

import com.example.sd40.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface EmployeeService  {

    List<Employee> findAll();
    void add(Employee cs);
    void delete(UUID id);
    void update(Employee cs);
    Employee detail(UUID id);
    List<Employee> timKiem(String name,String phone);
    List<Employee> timKiem2(String name,String phone);
    List<Employee> getByUserName(String username);
    Employee login(String username, String password);
    Employee checkRole(String username);
    Page<Employee> listDesc(Integer pageNo);
    List<Employee> checkUserNameUpdate(String tenHT,String tenSua);
}
