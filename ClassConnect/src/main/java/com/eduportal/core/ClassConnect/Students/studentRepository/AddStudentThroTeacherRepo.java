package com.eduportal.core.ClassConnect.Students.studentRepository;

import com.eduportal.core.ClassConnect.Students.studentsModel.AddStudentThroughTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddStudentThroTeacherRepo extends JpaRepository<AddStudentThroughTeacher, Long>{
}
