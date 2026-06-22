package com.jayasurya.employeemanagement.controller;

import com.jayasurya.employeemanagement.dto.EmployeeDTO;
import com.jayasurya.employeemanagement.entity.Employee;
import com.jayasurya.employeemanagement.response.ApiResponse;
import com.jayasurya.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping
    public ApiResponse<Employee> createEmployee(@Valid  @RequestBody EmployeeDTO dto) {

        Employee saved = service.saveEmployee(dto);

        return new ApiResponse<>(
                "Employee created successfully",
                true,
                saved
        );
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ApiResponse<Employee> getEmployeeById(@PathVariable Long id) {

        Employee emp = service.getEmployeeById(id);

        return new ApiResponse<>(
                "Employee fetched successfully",
                true,
                emp
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<Employee> updateEmployee(@PathVariable Long id,
                                   @RequestBody EmployeeDTO dto) {
        Employee  emp = service.updateEmployee(id, dto);
         return new ApiResponse<>(
                 "Employee updated  successfully",
                 true,
                 emp
         );

    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteEmployee(@PathVariable Long id) {

        service.deleteEmployee(id);

        return new ApiResponse<>(
                "Employee deleted successfully",
                true,
                "Deleted ID: " + id
        );
    }
}