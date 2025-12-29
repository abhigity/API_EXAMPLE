package com.api.service;

import com.api.controller.Exception.ResourceNotFound;
import com.api.dto.EmployeeDto;
import com.api.entity.Employee;
import com.api.repository.EmployeeRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class EmployeeService {
    
    private EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public void saveEmployee(Employee e) {
        employeeRepo.save(e);
    }

    public void deleteEmployeeById(long id) {
        employeeRepo.deleteById(id);
    }

    public EmployeeDto updateEmployee(long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepo.findById(id).get();

        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setNumber(employeeDto.getNumber());

        employeeRepo.save(employee);

        EmployeeDto employeeDto1 = new EmployeeDto();
        BeanUtils.copyProperties(employee,employeeDto1);
        return employeeDto1;
    }

    public List<Employee> getAllEmployee(int pageNo , int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Employee> page = employeeRepo.findAll(pageable);
        List<Employee> employees = page.getContent();
        return employees;
    }

    public Employee getEployeeById(Long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(
                () -> new ResourceNotFound("Record Not Found")
        );
//        return employee.get();
        return employee;
    }

    public Employee getEployeeByEmail(String email) {
        if(email!=null){
            Employee employee = employeeRepo.findByEmail(email);
            return employee;
        }
        return null;
    }
}
