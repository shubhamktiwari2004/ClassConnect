package com.eduportal.core.ClassConnect.Teacher.teachersController;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.ProfileModel;
import com.eduportal.core.ClassConnect.Teacher.teacherService.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TeacherProfileController {

    @Autowired
    ProfileService profile;

    @GetMapping("/teachers/profile/{teacherId}")
    @ResponseBody
    public List<ProfileModel> profile(@PathVariable int teacherId){
        List<ProfileModel> prof = profile.viewProfile(teacherId);
        return prof;
    }


    @GetMapping("/teachers/editProfile")
    public String editProfile(){
        return "teachers/profileEdit";
    }

}
