package com.api.controller;

import com.api.dto.ApiResponse;
import com.api.dto.EmployeeDto;
import com.api.entity.Employee;
import com.api.repository.EmployeeRepo;
import com.api.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.BeanInfo;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService,
                              EmployeeRepo employeeRepo) {
        this.employeeService = employeeService;
    }

    //http://localhost:8080/api/v1/employee/save
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<String>> saveEmployee(@RequestBody EmployeeDto employeeDto){
        Employee e = new Employee();
        BeanUtils.copyProperties(employeeDto,e);
        employeeService.saveEmployee(e);

        ApiResponse<String> response = new ApiResponse<>();
        response.setMessage("Done..");
        response.setData("Saved..!!");
        response.setStatusCode(201);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/v1/employee/deleteEmp?id=1
    @DeleteMapping("/deleteEmp")
    public ResponseEntity<ApiResponse<String>> deleteEmployee(@RequestParam long id){
        employeeService.deleteEmployeeById(id);

        ApiResponse<String> response = new ApiResponse<>();
        response.setMessage("Done..");
        response.setData("Record Deleted..!!");
        response.setStatusCode(200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/employee/updateEmp?id=1
    @PutMapping("/updateEmp")
    public ResponseEntity<ApiResponse<EmployeeDto>> updateEmployee(@RequestParam long id, @RequestBody EmployeeDto employeeDto){
        EmployeeDto employeeDto1 = employeeService.updateEmployee(id, employeeDto);

        ApiResponse<EmployeeDto> response = new ApiResponse<>();
        response.setMessage("Employee Record Updated..!!!");
        response.setData(employeeDto1);
        response.setStatusCode(200);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/employee/allEmp?pageNo=0&pageSize=4
    @GetMapping("/allEmp")
    public ResponseEntity<ApiResponse<List<Employee>>> getAllEmp(
            @RequestParam(value = "pageNo" , defaultValue = "0", required = false) int pageNo ,
            @RequestParam(value = "pageSize" , defaultValue = "4" , required = false) int pageSize){

        List<Employee> employees = employeeService.getAllEmployee(pageNo,pageSize);

        ApiResponse<List<Employee>> response = new ApiResponse<>();
        response.setMessage("All Employees Record..!!!");
        response.setData(employees);
        response.setStatusCode(200);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/employee/id?id=1
    @GetMapping("/id")
    public ResponseEntity<ApiResponse<Employee>> getEmployeeDetailById(@RequestParam Long id){
        Employee emp = employeeService.getEployeeById(id);
        ApiResponse<Employee> res = new ApiResponse<>();
        res.setMessage("Employee Record Fetched");
        res.setData(emp);
        res.setStatusCode(200);

        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/employee/id?id=1
    @GetMapping("/email")
    public ResponseEntity<ApiResponse<?>> getEmployeeDetailByEmail(@RequestBody Employee employee){
        Employee emp = employeeService.getEployeeByEmail(employee.getEmail());
        if(emp!=null){
            ApiResponse<Employee> res = new ApiResponse<>();
            res.setMessage("Employee Record Fetched");
            res.setData(emp);
            res.setStatusCode(200);

            return new ResponseEntity<>(res,HttpStatus.OK);
        }else{
            ApiResponse<String> res = new ApiResponse<>();
            res.setMessage("Record not found");
            res.setData("provide valid email id");
            res.setStatusCode(400);

            return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
        }

    }
}
