package com.jayasurya.employeemanagement.service;

import com.jayasurya.employeemanagement.dto.EmployeeDTO;
import com.jayasurya.employeemanagement.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(EmployeeDTO dto);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, EmployeeDTO dto);

    void deleteEmployee(Long id);
}