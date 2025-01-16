package com.eduportal.core.ClassConnect.Teacher.teacherModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_marks")
public class FetchStudentsModel {

    @Id
    private int id;
    private int student_id;
    private String subject;
    private int unit1;
    private int sem1;
    private int unit2;
    private int sem2;
    private int total;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getUnit1() {
        return unit1;
    }

    public void setUnit1(int unit1) {
        this.unit1 = unit1;
    }

    public int getSem1() {
        return sem1;
    }

    public void setSem1(int sem1) {
        this.sem1 = sem1;
    }

    public int getUnit2() {
        return unit2;
    }

    public void setUnit2(int unit2) {
        this.unit2 = unit2;
    }

    public int getSem2() {
        return sem2;
    }

    public void setSem2(int sem2) {
        this.sem2 = sem2;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

}
