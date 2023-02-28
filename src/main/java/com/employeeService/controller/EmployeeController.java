package com.employeeService.controller;

import com.employeeService.entity.Employee;
import com.employeeService.services.AttendanceService;
import com.employeeService.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
//@RequestMapping("/v1/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AttendanceService attendanceService;


    @RequestMapping("/")
    String showAddEmployeeForm(@ModelAttribute("employee") Employee emp, Model model) {
        //  model.addAttribute("employee",emp);

        return "addEmployee";
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    String confirmEmployee(@ModelAttribute("employee") Employee emp) {
        employeeService.save(emp);
        //model.addAttribute("employeeList",employeeService.getListOfEmployees());

        return "redirect:/showEmployees";
    }

    @RequestMapping("/showEmployees")
    String showListOfEmployees(Model model) {
        List<Employee> employeeList = employeeService.getListOfEmployees();
        //System.out.println(">>>>>>>>>>>>>employee list employee"+employeeList.get(0));
        model.addAttribute("employeeList", employeeList);
        return "listOfEmployees";

    }

    @RequestMapping("/update")
    String updateEmployee(@RequestParam("id") int id, Model model) {

        Employee employee = employeeService.findById(id);

        System.out.println(">>>>>>>>>>>employee for update is " + employee.getId() + "" + employee.getName());
        model.addAttribute("employee", employee);
        return "/addEmployee";


    }

    @RequestMapping("/delete/{id}")
    String deleteEmployee(@PathVariable("id") int id, Model model) {

        employeeService.deleteById(id);


        return "redirect:/showEmployees";


    }


}


