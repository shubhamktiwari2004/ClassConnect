package com.eduportal.core.ClassConnect.Students.studentRepository;

import com.eduportal.core.ClassConnect.Students.studentsModel.StudentLoginModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentProfileRepo extends JpaRepository<StudentLoginModel , Long> {

    @Query(value = "SELECT * from student_details where student_id = ?1" , nativeQuery = true)
    StudentLoginModel viewProfile(Long studentId);


    @Modifying
    @Transactional
    @Query(value = "UPDATE student_details SET first_name = ?2, last_name = ?3,email = ?4, address = ?5 , image_url = ?6 WHERE student_id = ?1", nativeQuery = true)
    void updateProfileById(Long studentId, String firstName, String lastName, String email, String address, String imageUrl);
}
