package com.eduportal.core.ClassConnect.Teacher.teacherRepository;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchStudentsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FetchStudentsRepository extends JpaRepository<FetchStudentsModel,Long>{
    //Query to get the student based on the roll no
    @Query("SELECT sd FROM AddStudentModel sd WHERE sd.id = :id AND sd.is_active = '0'")
    List<AddStudentModel> findStudentById(@Param("id") int id);

    // Query to fetch students by class division ID, sorted by roll number
    @Query(value = "SELECT * FROM class_connect.student_marks WHERE id = :id", nativeQuery = true)
    List<FetchStudentsModel> findStudentMarks(@Param("id") int id);

    @Query("SELECT sd FROM AddStudentModel sd WHERE sd.is_active = '0' ORDER BY sd.id")
    List<AddStudentModel> getAllStudents();


}
