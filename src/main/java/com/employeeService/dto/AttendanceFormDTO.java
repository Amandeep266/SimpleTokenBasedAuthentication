package com.employeeService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;
public class AttendanceFormDTO {

@JsonFormat(pattern="yyyy-MM-dd")
@DateTimeFormat(pattern = "yyyy-MM-dd")
@NotNull(message = "cannot be blank")
    private LocalDate dateOfAttendanceMark;
    private int id;


@Min(value = 0, message="cannot be negative")
@Max(value=24,message="cannot exceeds 24")
@NotNull(message = "cannot be empty")
private int numberOfHours;

    public AttendanceFormDTO() {
       super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }
    public LocalDate getDateOfAttendanceMark() {
        return dateOfAttendanceMark;
    }

    public void setDateOfAttendanceMark(LocalDate dateOfAttendanceMark) {
        this.dateOfAttendanceMark = dateOfAttendanceMark;
    }

}
