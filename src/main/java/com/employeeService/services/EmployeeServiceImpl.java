package com.employeeService.services;

import com.employeeService.Repository.EmployeeRepository;
import com.employeeService.entity.Attendance;
import com.employeeService.entity.AttendanceId;
import com.employeeService.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AttendanceService attendanceService;

    @Override
    public void save(Employee employee) {

    LocalDate date = LocalDate.now();
    System.out.println(date);
    Month month = date.getMonth();
    int year = date.getYear();

    System.out.println(">>>>>id of employee is" + employee.getId());

    Employee savedEmployee = employeeRepository.save(employee);
    System.out.println(">>>>>>>>>>>>>>saved employee id" + savedEmployee.getId());
    AttendanceId id = new AttendanceId(savedEmployee.getId(), month, date.getDayOfMonth(),year);
    Attendance attendance = new Attendance(id, 1, 0);

    attendanceService.addAttendanceOfEmployee(attendance);

}






    @Override
    public HttpStatus update(Employee employee) {
        employeeRepository.save(employee);
        return  HttpStatus.OK;
    }

    @Override
    public void deleteById(int id) {
        try{
            employeeRepository.deleteById(id);
       System.out.println("employee deleted");
        }
        catch(Exception e)
        {
System.out.println(">>>>>>>>>>cannot delete>>>>>>.");
        }
    }

    @Override
    public Employee findById(int id) {

        Employee emp= employeeRepository.findById(id).get();
        return  emp!=null? emp:null;
    }

    @Override
    public List<Employee> getListOfEmployees() {

        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList;
    }


}
