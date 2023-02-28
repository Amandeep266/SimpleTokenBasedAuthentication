//package com.employeeService.validator;
//
//import com.employeeService.dto.AttendanceFormDTO;
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//
//import java.util.Date;
//
//public class AttendanceFormDTOValidator {
//
//    public boolean supports(Class clazz) {
//        return AttendanceFormDTO.class.equals(clazz);
//    }
//
//    public void validate(Object obj, Errors e) {
//        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
//        AttendanceFormDTO dto = (AttendanceFormDTO) obj;
//        if (dto.getNumberOfHours() > 24) {
//            e.rejectValue("numberOfHours", "Number of hours cannot be more than 24");
//        }
////        if(!isValid(dto.getDateOfAttendanceMark()))
////        {
////            e.rejectValue("dateOfAttendanceMark", "Cannot add attendance");
////        }
//    }
//    private boolean isValid(Date date)
//    {
//
//        return false;
//    }
//}
