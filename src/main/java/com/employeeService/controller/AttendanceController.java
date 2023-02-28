package com.employeeService.controller;

import com.employeeService.dto.AttendanceFormDTO;
import com.employeeService.entity.Attendance;
import com.employeeService.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.BindException;
import java.text.ParseException;

@Controller
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @RequestMapping("/attendanceForm")
    String showAttendanceForm(@RequestParam("id") int id, Model model) {

        AttendanceFormDTO attendanceFormDTO = new AttendanceFormDTO();
        attendanceFormDTO.setId(id);
        model.addAttribute("attendance", attendanceFormDTO);

        return "/attendanceForm";
    }

    @RequestMapping("/addAttendance")
    String addAttendance(@Valid @ModelAttribute("attendance") AttendanceFormDTO attendance, BindingResult bindingResult, Model model) throws ParseException {

        System.out.println(">>>>>>>>>>>>" + attendance.getDateOfAttendanceMark());
        System.out.println(attendance.getNumberOfHours());

        if (bindingResult.hasErrors())
            return "attendanceForm";
        else if (attendanceService.save(attendance))
            return "redirect:/showEmployees";
        else {
            ObjectError error = new ObjectError
                    ("dateOfAttendanceMark", "Invalid Date for attendance");
            bindingResult.addError(error);

            return "attendanceForm";
        }
    }
}
