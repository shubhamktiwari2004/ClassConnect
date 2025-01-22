package com.eduportal.core.ClassConnect.Students.studentRepository;

import com.eduportal.core.ClassConnect.Students.studentsModel.AddStudentThroughTeacher;
import com.eduportal.core.ClassConnect.Students.studentsModel.StudentLoginModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddStudentThroTeacherRepo extends JpaRepository<AddStudentThroughTeacher, Long>{

    @Query(value = "SELECT * from students WHERE username = ?1 AND is_active = 0"  , nativeQuery = true)
    Optional<AddStudentThroughTeacher> findByUsername(String username);


    @Modifying
    @Transactional
    @Query("UPDATE AddStudentThroughTeacher sd SET sd.is_active = '1' WHERE id = ?1")
    void deleteStudent(int studentId);
}
