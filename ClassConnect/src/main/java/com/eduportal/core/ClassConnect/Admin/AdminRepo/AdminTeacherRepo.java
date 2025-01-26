package com.eduportal.core.ClassConnect.Admin.AdminRepo;

import com.eduportal.core.ClassConnect.Admin.AdminModel.AdminTeacherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AdminTeacherRepo extends JpaRepository<AdminTeacherModel , Long> {

    @Query("SELECT t FROM AdminTeacherModel t") // Use the entity name, not the table name
    ArrayList<AdminTeacherModel> getTeacherList();
}
