package com.eduportal.core.ClassConnect.Teacher.teacherRepository;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchClassDivision;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchScheduleModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchStudentsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FetchStudentsRepository extends JpaRepository<FetchStudentsModel,Long>{
    // Query to fetch students by class division ID, sorted by roll number
    @Query(value = "SELECT * FROM class_connect.students WHERE class_and_divisions_id = :id ORDER BY roll_no ASC", nativeQuery = true)
    List<FetchStudentsModel> findStudentsByClassDivisionId(@Param("id") int id);

    @Query("SELECT f FROM FetchClassDivision f ORDER BY f.id")
    List<FetchClassDivision> getAllClassDivision();
}
