package com.eduportal.core.ClassConnect.Teacher.teacherRepository;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.ProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepo extends JpaRepository<ProfileModel, Long>{



    @Query(value = "Select * from teacher_profile where teacher_id =?1", nativeQuery = true)
    List<ProfileModel> getByTeacherId(int teacher_id);
}
