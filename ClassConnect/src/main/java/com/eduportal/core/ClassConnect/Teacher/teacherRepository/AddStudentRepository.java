package com.eduportal.core.ClassConnect.Teacher.teacherRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AddStudentRepository extends JpaRepository<Student , Long> {

}
