package com.jayasurya.employeemanagement.service;

import com.jayasurya.employeemanagement.dto.EmployeeDTO;
import com.jayasurya.employeemanagement.entity.Employee;
import com.jayasurya.employeemanagement.exception.ResourceNotFoundException;
import com.jayasurya.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public Employee saveEmployee(EmployeeDTO dto) {

        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());

        return repository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));}

    @Override
    public Employee updateEmployee(Long id, EmployeeDTO dto) {

        Employee existing = repository.findById(id).orElse(null);

        if (existing != null) {
            existing.setName(dto.getName());
            existing.setEmail(dto.getEmail());
            existing.setDepartment(dto.getDepartment());
            return repository.save(existing);
        }

        return null;
    }

    @Override
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}