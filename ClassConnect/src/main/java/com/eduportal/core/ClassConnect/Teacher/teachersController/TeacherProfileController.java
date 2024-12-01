package com.eduportal.core.ClassConnect.Teacher.teachersController;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.ProfileModel;
import com.eduportal.core.ClassConnect.Teacher.teacherService.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TeacherProfileController {

    @Autowired
    ProfileService profile;

    @PostMapping("/teachers/profile")
    public String profile(@RequestParam int teacherId , Model model){
        List<ProfileModel> prof = profile.viewProfile(teacherId);
        model.addAttribute("profile" , prof.getFirst());
        return "teachers/profile";
    }


    @GetMapping("/teachers/editProfile")
    public String editProfile(){
        return "teachers/profileEdit";
    }

}
