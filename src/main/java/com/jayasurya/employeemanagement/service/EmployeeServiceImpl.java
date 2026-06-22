package com.jayasurya.employeemanagement.service;

import com.jayasurya.employeemanagement.dto.EmployeeDTO;
import com.jayasurya.employeemanagement.entity.Employee;
import com.jayasurya.employeemanagement.exception.ResourceNotFoundException;
import com.jayasurya.employeemanagement.repository.EmployeeRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    private EmployeeRepository repository;

    @Override
    public Employee saveEmployee(EmployeeDTO dto) {

        logger.info("Creating new employee with email: {}", dto.getEmail());

        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());

        Employee saved = repository.save(employee);

        logger.info("Employee created with id: {}", saved.getId());

        return saved;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        logger.error("Employee not found with id: {}", id);

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeDTO dto) {

        logger.info("Updating employee with id: {}", id);

        Employee existing = repository.findById(id).orElse(null);

        if (existing != null) {
            existing.setName(dto.getName());
            existing.setEmail(dto.getEmail());
            existing.setDepartment(dto.getDepartment());

            logger.info("Employee updated successfully: {}", id);
            return repository.save(existing);
        }

        logger.warn("Update failed - employee not found: {}", id);
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {

        logger.info("Deleting employee with id: {}", id);

        if (!repository.existsById(id)) {
            logger.error("Delete failed - employee not found: {}", id);
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        }

        repository.deleteById(id);
        logger.info("Employee deleted successfully: {}", id);
    }
}