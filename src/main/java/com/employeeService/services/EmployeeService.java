package com.employeeService.services;

import com.employeeService.entity.Employee;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface EmployeeService {
void save(Employee employee);
HttpStatus update(Employee employee);
void deleteById(int id);
Employee findById(int id);
List<Employee> getListOfEmployees();

}

