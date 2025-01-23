package com.eduportal.core.ClassConnect.Students.studentRepository;

import com.eduportal.core.ClassConnect.Students.studentsModel.StudentDashboardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDashboardRepo extends JpaRepository<StudentDashboardModel, Long> {

    @Query("SELECT d FROM StudentDashboardModel d WHERE d.student_id = :studentId")
    List<StudentDashboardModel> findStudentMarksById(@Param("studentId") Long studentId);
}
