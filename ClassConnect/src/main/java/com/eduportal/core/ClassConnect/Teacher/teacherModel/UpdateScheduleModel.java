package com.eduportal.core.ClassConnect.Teacher.teacherModel;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "schedule")
public class UpdateScheduleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schedule_id;
    private long teacherId; // Assuming you want to link the schedule to a teacher
    private String className;
    private String subject;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String topic;
    private String roomNumber;
    private String notes;

    public UpdateScheduleModel(int schedule_id, String className, String subject, LocalDate date, LocalTime startTime, LocalTime endTime, String topic, String roomNumber, String notes) {
        this.schedule_id = schedule_id;
        this.className = className;
        this.subject = subject;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.topic = topic;
        this.roomNumber = roomNumber;
        this.notes = notes;
    }



    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int id) {
        this.schedule_id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }



}
