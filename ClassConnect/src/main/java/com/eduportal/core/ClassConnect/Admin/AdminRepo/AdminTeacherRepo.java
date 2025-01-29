package com.eduportal.core.ClassConnect.Admin.AdminRepo;

import com.eduportal.core.ClassConnect.Admin.AdminModel.AdminTeacherModel;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AdminTeacherRepo extends JpaRepository<AdminTeacherModel , Long> {

    @Query("SELECT t FROM AdminTeacherModel t WHERE is_deleted=0") // Use the entity name, not the table name
    ArrayList<AdminTeacherModel> getTeacherList();

    @Query("SELECT t FROM AdminTeacherModel t WHERE t.teacher_id = :id AND is_deleted=0")
    List<AdminTeacherModel> getTeacherById(@Param("id") Long id);
}
