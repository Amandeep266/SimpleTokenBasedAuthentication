package com.employeeService.Repository;

import com.employeeService.entity.Attendance;
import com.employeeService.entity.AttendanceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, AttendanceId> {
//    @Query(value="select id, as emp_id,year,month,(select count(date) group by month) as" +
//            " total,sum(present) as present ,sum(lop) as lop from attendance group by id,year,month",
//            nativeQuery = true)
//    List<MonthlyAttendanceDTO> getMonthlyAttendanceList() ;

}
