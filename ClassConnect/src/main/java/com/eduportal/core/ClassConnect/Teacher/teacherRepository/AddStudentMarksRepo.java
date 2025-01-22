package com.eduportal.core.ClassConnect.Teacher.teacherRepository;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentMarksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AddStudentMarksRepo extends JpaRepository<AddStudentMarksModel, Long> {

    // Query to fetch students by class division ID, sorted by roll number
    @Query(value = "SELECT * FROM class_connect.student_marks WHERE student_id =?1", nativeQuery = true)
    ArrayList<AddStudentMarksModel> findStudentMarks(Long student_id);

}
