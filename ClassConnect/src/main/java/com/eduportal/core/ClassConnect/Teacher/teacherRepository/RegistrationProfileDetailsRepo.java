package com.eduportal.core.ClassConnect.Teacher.teacherRepository;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.LoginModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.RegistrationProfileDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationProfileDetailsRepo extends JpaRepository<RegistrationProfileDetails, Long> {

    Optional<RegistrationProfileDetails> findByUsername(String username);

    @Query("SELECT r FROM RegistrationProfileDetails r WHERE r.teacher_id = :teacher_id")
    Optional<RegistrationProfileDetails> findByTeacherId(@Param("teacher_id") Long teacher_id);

    @Modifying
    @Transactional
    @Query("UPDATE RegistrationProfileDetails t SET t.is_deleted = 1 WHERE t.teacher_id = :teacher_id")
    void softDeleteTeacher(Long teacher_id);
}
