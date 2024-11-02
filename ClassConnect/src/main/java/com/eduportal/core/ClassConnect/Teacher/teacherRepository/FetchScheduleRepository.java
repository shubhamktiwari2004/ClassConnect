package com.eduportal.core.ClassConnect.Teacher.teacherRepository;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FetchScheduleRepository extends JpaRepository<FetchScheduleModel,Long> {
    @Query(value = "SELECT * FROM class_connect.schedule ORDER BY date DESC", nativeQuery = true)
    List<FetchScheduleModel> getAllSchedule();
}
