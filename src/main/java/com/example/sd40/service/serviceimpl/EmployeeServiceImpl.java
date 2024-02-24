package com.example.sd40.service.serviceimpl;

import com.example.sd40.entity.Employee;
import com.example.sd40.repository.EmployeeRepository;
import com.example.sd40.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void add(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void delete(UUID id) {
        employeeRepository.deleteById(id);
    }




    @Override
    public Employee detail(UUID id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public List<Employee> timKiem(String name, String phone) {

        return employeeRepository.timKiem(name,phone);
    }

    @Override
    public List<Employee> timKiem2(String name, String phone) {

        return employeeRepository.timKiem2(name,phone);
    }

    @Override
    public List<Employee> getByUserName(String username) {
        return employeeRepository.getByUserName(username);
    }

    @Override
    public Employee login(String username, String password) {
        return employeeRepository.login(username,password);
    }

    @Override
    public Employee checkRole(String username) {
        return employeeRepository.checkRole(username);
    }

    @Override
    public Page<Employee> listDesc(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo,10);
        return employeeRepository.listDesc(pageable);
    }

    @Override
    public List<Employee> checkUserNameUpdate(String tenHT, String tenSua) {
        return employeeRepository.checkUserNameUpdate(tenHT, tenSua);
    }

    @Override
    public void update(Employee cs) {

        this.employeeRepository.save(cs);

    }
}
