package com.eduportal.core.ClassConnect.Teacher.teacherModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "class_and_divisions")
public class FetchClassDivision {

    @Id
    private int id;
    private int std;
    private String division;

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStd() {
        return std;
    }

    public void setStd(int std) {
        this.std = std;
    }
}
