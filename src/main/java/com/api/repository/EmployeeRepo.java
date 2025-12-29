package com.api.repository;

import com.api.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {


    @Query("select e from Employee e where e.email = :email")
    public Employee findByEmail(@Param("email") String email);

}
