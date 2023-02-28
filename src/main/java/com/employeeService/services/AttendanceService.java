package com.employeeService.services;

import com.employeeService.dto.AttendanceFormDTO;
import com.employeeService.entity.Attendance;

import java.text.ParseException;
import java.util.List;

public interface AttendanceService {
    boolean save(AttendanceFormDTO attendanceFormDTO) throws ParseException;
    List<Attendance> getAttendanceById(int employeeId);
    void update(int attendanceId);
    void addAttendanceOfEmployee(Attendance attendance);
    //List<MonthlyAttendanceDTO> showMonthlyAttendanceList();


}
