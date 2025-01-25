package com.eduportal.core.ClassConnect.Admin.AdminRepo;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddTeacherRepo extends JpaRepository<LoginModel, Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
