package com.eduportal.core.ClassConnect.Teacher.teacherRepository;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.UpdateScheduleModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;

public interface UpdateScheduleRepository extends JpaRepository<UpdateScheduleModel, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE UpdateScheduleModel c SET c.className = ?2, c.subject = ?3, c.date = ?4, c.startTime = ?5, c.endTime = ?6, c.topic = ?7, c.roomNumber = ?8, c.notes = ?9 WHERE c.schedule_id = ?1")
    void updateScheduleById(int scheduleId, String className, String subject, LocalDate date, LocalTime startTime, LocalTime endTime, String topic, String roomNumber, String notes);
}
