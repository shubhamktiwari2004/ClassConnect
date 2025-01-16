package com.eduportal.core.ClassConnect.Teacher.teacherModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_marks")
public class AddStudentMarks {

    private String subject;
    private int unit1;
    private int sem1;
    private int unit2;
    private int sem2;
    private int total;
}
