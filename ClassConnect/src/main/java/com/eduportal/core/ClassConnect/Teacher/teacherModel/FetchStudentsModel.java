package com.eduportal.core.ClassConnect.Teacher.teacherModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class FetchStudentsModel {

    @Id
    private int id;
    private int roll_no;
    private String students_name;
    private int maths_marks;
    private int science_marks;
    private int hindi_marks;
    private int english_marks;
    private int total_marks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(int roll_no) {
        this.roll_no = roll_no;
    }

    public String getStudents_name() {
        return students_name;
    }

    public void setStudents_name(String students_name) {
        this.students_name = students_name;
    }

    public int getMaths_marks() {
        return maths_marks;
    }

    public void setMaths_marks(int maths_marks) {
        this.maths_marks = maths_marks;
    }

    public int getScience_marks() {
        return science_marks;
    }

    public void setScience_marks(int science_marks) {
        this.science_marks = science_marks;
    }

    public int getHindi_marks() {
        return hindi_marks;
    }

    public void setHindi_marks(int hindi_marks) {
        this.hindi_marks = hindi_marks;
    }

    public int getEnglish_marks() {
        return english_marks;
    }

    public void setEnglish_marks(int english_marks) {
        this.english_marks = english_marks;
    }

    public int getTotal_marks() {
        return total_marks;
    }

    public void setTotal_marks(int total_marks) {
        this.total_marks = total_marks;
    }
}
