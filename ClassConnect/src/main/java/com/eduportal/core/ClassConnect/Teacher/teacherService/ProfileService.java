package com.eduportal.core.ClassConnect.Teacher.teacherService;


import com.eduportal.core.ClassConnect.Teacher.teacherModel.ProfileModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    ProfileRepo profile;

    public List<ProfileModel> viewProfile(int teacherId){
        List<ProfileModel> prof =profile.getByTeacherId(teacherId);
        return prof;
    }
}
