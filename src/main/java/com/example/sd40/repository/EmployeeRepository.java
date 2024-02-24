package com.example.sd40.repository;

import com.example.sd40.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query("SELECT e from Employee e WHERE e.fullName=?1 and e.phoneNumber=?2")
    List<Employee> timKiem(String name,String phone);


    @Query("SELECT e from Employee e WHERE e.fullName=?1 or e.phoneNumber=?2")
    List<Employee> timKiem2(String name,String phone);

    @Query("SELECT c from Employee c where c.username=?1")
    List<Employee> getByUserName(String username);

    @Query("SELECT c from Employee c where c.username=?1 and c.password=?2")
    Employee login(String username, String password);

    @Query("SELECT c from Employee c where c.username=?1 and c.role.name='Staff'")
    Employee checkRole(String username);

    @Query("select c from Employee c order by c.datecreated desc ")
    Page<Employee> listDesc(Pageable pageable);

    @Query("select c from Employee c where c.id not in (select d.id from Employee d where d.username =?1) and c.username =?2")
    List<Employee> checkUserNameUpdate(String tenHT,String tenSua);
}
