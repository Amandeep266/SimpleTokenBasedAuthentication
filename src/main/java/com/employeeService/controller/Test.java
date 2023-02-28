package com.employeeService.controller;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Test {
    public static void main(String args[])
    {

        LocalDate date= LocalDate.of(2022, 7, 27);
        System.out.println(date);

        long daysBetween = DAYS.between( LocalDate.of(2022, 7, 25),LocalDate.now());
        System.out.println(daysBetween);
    }
}
