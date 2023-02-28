package com.employeeService.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.Month;

@Embeddable
public class AttendanceId implements Serializable {


    @Column
    private int emp_id;




    @Column
    private int year;
    @Column
    private int date;


    @Column
    private Month month;

    public AttendanceId()
    {
        super();
    }
    public AttendanceId(int empId, Month month,int date,int year) {
        this.emp_id= empId;
        this.month = month;
        this.date=date;
        this.year=year;

    }
    public int getId() {
        return emp_id;
    }

    public void setId(int id) {
        this.emp_id = id;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
