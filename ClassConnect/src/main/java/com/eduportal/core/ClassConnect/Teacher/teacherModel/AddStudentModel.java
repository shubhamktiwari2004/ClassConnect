package com.eduportal.core.ClassConnect.Teacher.teacherModel;

import jakarta.persistence.*;

@Entity
@Table(name = "students") // Database table name
public class AddStudentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    private Long id;

    @Column(name = "class_and_divisions_id")
    private Long classDivisionsId;

    @Column(name = "roll_no")
    private int RollNo;

    @Column(name = "students_name")
    private String studentName;

    @Column(name = "maths_marks")
    private int mathsMarks;

    @Column(name = "science_marks")
    private int scienceMarks;

    @Column(name = "hindi_marks")
    private int hindiMarks;

    @Column(name = "english_marks")
    private int englishMarks;

    @Column(name = "total_marks")
    private int totalMarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRollNo() {
        return RollNo;
    }

    public void setRollNo(int RollNo) {
        this.RollNo = RollNo;
    }

    public Long getClassDivisionsId() {
        return classDivisionsId;
    }

    public void setClassDivisionsId(Long classDivisionsId) {
        this.classDivisionsId = classDivisionsId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getMathsMarks() {
        return mathsMarks;
    }

    public void setMathsMarks(int mathsMarks) {
        this.mathsMarks = mathsMarks;
    }

    public int getScienceMarks() {
        return scienceMarks;
    }

    public void setScienceMarks(int scienceMarks) {
        this.scienceMarks = scienceMarks;
    }

    public int getHindiMarks() {
        return hindiMarks;
    }

    public void setHindiMarks(int hindiMarks) {
        this.hindiMarks = hindiMarks;
    }

    public int getEnglishMarks() {
        return englishMarks;
    }

    public void setEnglishMarks(int englishMarks) {
        this.englishMarks = englishMarks;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }
}
