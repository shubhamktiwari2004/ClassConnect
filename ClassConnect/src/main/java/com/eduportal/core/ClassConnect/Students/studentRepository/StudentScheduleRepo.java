package com.eduportal.core.ClassConnect.Students.studentRepository;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.ScheduleModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface StudentScheduleRepo extends JpaRepository<ScheduleModel,Long> {

    @Query(value = "SELECT * FROM schedule WHERE teacher_id = :teacher_id", nativeQuery = true)
    @Transactional
    ArrayList<ScheduleModel> findByTeacherId(@Param("teacher_id") Integer teacherId);

}
