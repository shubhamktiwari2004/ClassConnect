package com.eduportal.core.ClassConnect.Teacher.teacherRepository;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.AddStudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddStudentRepository extends JpaRepository<AddStudentModel, Long> {
    // Custom queries (if needed) can be added here
}
