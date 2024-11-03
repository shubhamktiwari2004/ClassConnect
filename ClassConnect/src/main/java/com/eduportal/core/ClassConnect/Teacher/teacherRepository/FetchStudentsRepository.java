package com.eduportal.core.ClassConnect.Teacher.teacherRepository;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchScheduleModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchStudentsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FetchStudentsRepository extends JpaRepository<FetchStudentsModel,Long>{

    @Query(value = "SELECT * FROM class_connect.students ORDER BY id DESC", nativeQuery = true)
    List<FetchStudentsModel> getAllStudents();
}
