package com.eduportal.core.ClassConnect.Students.studentRepository;


import com.eduportal.core.ClassConnect.Students.studentsModel.StudentLoginModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface StudentLoginRepo extends JpaRepository<StudentLoginModel , Long> {


    @Query(value = "SELECT * from student_details WHERE username = ?1 AND is_active = 0"  , nativeQuery = true)
    Optional<StudentLoginModel> findByUsername(String username);

    @Query(value = "SELECT * from student_details WHERE email = ?1 AND is_active = 0"  , nativeQuery = true)
    Optional<StudentLoginModel> findByEmail(String email);


    @Query(value = "SELECT * from student_details WHERE student_id = ?1 AND is_active = 0"  , nativeQuery = true)
    ArrayList<StudentLoginModel> findStudentById(Long id);

}

