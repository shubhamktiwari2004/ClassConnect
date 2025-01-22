package com.eduportal.core.ClassConnect.Teacher.teacherRepository;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FetchStudentsRepository extends JpaRepository<AddStudentModel,Long>{
    //Query to get the student based on the roll no
    @Query("SELECT sd FROM AddStudentModel sd WHERE sd.student_id = :student_id AND sd.is_active = '0'")
    List<AddStudentModel> findStudentById(@Param("student_id") Long student_id);

    @Query("SELECT sd FROM AddStudentModel sd WHERE sd.is_active = '0' AND sd.teacher_id = ?1 ORDER BY sd.id")
    List<AddStudentModel> getAllStudents(int teacherId);

    @Modifying
    @Transactional
    @Query("UPDATE AddStudentModel sd SET sd.is_active = '1' WHERE student_id = ?1")
    void deleteStudent(Long studentId);



}
