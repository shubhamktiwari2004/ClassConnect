package com.eduportal.core.ClassConnect.Teacher.teacherRepository;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.ProfileModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepo extends JpaRepository<ProfileModel, Long>{



    @Query(value = "Select * from teacher_profile where teacher_id =?1", nativeQuery = true)
    List<ProfileModel> getByTeacherId(Long teacher_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE teacher_profile SET name = ?2, email = ?3, mobile = ?4, address = ?5 , profile_image = ?6 WHERE teacher_id = ?1", nativeQuery = true)
    void updateProfileById(Long teacherId, String name, String email, Long mobile, String address , String imageUrl);

}
