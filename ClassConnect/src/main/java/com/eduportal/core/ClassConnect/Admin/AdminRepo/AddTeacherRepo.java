package com.eduportal.core.ClassConnect.Admin.AdminRepo;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.LoginModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddTeacherRepo extends JpaRepository<LoginModel, Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    Optional<LoginModel> findByUsername(String username);

    @Query("SELECT p FROM LoginModel p WHERE p.teacher_id = :teacher_id")
    Optional<LoginModel> findByTeacherId(@Param("teacher_id") Long teacher_id);

    @Modifying
    @Transactional
    @Query("UPDATE LoginModel t SET t.isDeleted = 1 WHERE t.teacher_id = :teacher_id")
    void softDeleteTeacher(@Param("teacher_id") Long teacher_id);
}
