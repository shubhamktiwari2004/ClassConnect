package com.eduportal.core.ClassConnect.Admin.AdminRepo;

import com.eduportal.core.ClassConnect.Students.studentsModel.StudentLoginModel;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AdminDashboardRepo extends JpaRepository<StudentLoginModel,Long> {

    @Query(value = "SELECT * FROM student_details s WHERE s.teacher_id = :id AND s.is_active = 0", nativeQuery = true)
    ArrayList<StudentLoginModel> getStudentList(@Param("id") int id);
}
