package com.employeeService.services;

import com.employeeService.Repository.AttendanceRepository;
import com.employeeService.dto.AttendanceFormDTO;
import com.employeeService.entity.Attendance;
import com.employeeService.entity.AttendanceId;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    AttendanceRepository attendanceRepository;

    @Override
    public boolean save(AttendanceFormDTO attendanceFormDTO) throws ParseException {
//        Date markedDate=new SimpleDateFormat("dd/MM/yyyy").
//                parse(attendanceFormDTO.getDateOfAttendanceMark());
//        String markedDate1= new SimpleDateFormat("yyyy-MM-dd").format(markedDate);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String strDate = dateFormat.format(attendanceFormDTO.getDateOfAttendanceMark());
        LocalDate dateToValidate = LocalDate.parse(strDate);
        LocalDate nextDayOfCurrent = LocalDate.now().plusDays(1);
        LocalDate todayDate = LocalDate.now();
        LocalDate beforeCurrentDate = LocalDate.now().minusDays(2);
        long daysBetween = DAYS.between(dateToValidate, todayDate);
        System.out.println("DIB : " + daysBetween);

        if (daysBetween <= 2 && daysBetween >= 0) {
            int present = markPresentOnNumberOfHours(attendanceFormDTO.getNumberOfHours());
            int lop = present == 1 ? 0 : 1;
            AttendanceId id = new AttendanceId(attendanceFormDTO.getId(), dateToValidate.getMonth(), dateToValidate.getDayOfMonth(), dateToValidate.getYear());
            System.out.println(">>>>>>>>>" + id.toString() + ">>>>>>>>");

            Attendance attendance = new Attendance(id, lop, present);
            attendanceRepository.save(attendance);
            return true;
        } else {
            System.out.println("date of attendnace not valid!!!!");
            return false;
        }
//        if (dateToValidate.before(nextDayOfCurrent.)
//                && dateToValidate.after(beforeCurrentDate.getTime()))
    }

    private int markPresentOnNumberOfHours(int numberOfHours) {

        if (numberOfHours <= 24 && numberOfHours >= 4) {

            return 1;
        }
        return 0;

    }

    @Override
    public List<Attendance> getAttendanceById(int employeeId) {
        return null;
    }


    @Override
    public void update(int attendanceId) {

    }

    @Override
    public void addAttendanceOfEmployee(Attendance attendance) {

        attendanceRepository.save(attendance);
    }

//    @Override
//    public List<MonthlyAttendanceDTO> showMonthlyAttendanceList() {
//
//        return attendanceRepository.getMonthlyAttendanceList();
//    }


}
