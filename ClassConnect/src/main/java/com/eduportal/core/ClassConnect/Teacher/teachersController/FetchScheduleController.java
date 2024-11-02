package com.eduportal.core.ClassConnect.Teacher.teachersController;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchScheduleModel;
import com.eduportal.core.ClassConnect.Teacher.teacherService.FetchScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FetchScheduleController {


    @Autowired
    private FetchScheduleService scheduleService;

    @GetMapping("/teachers/schedule")
    public String getAllSchedules(Model model){
        List<FetchScheduleModel> schedules = scheduleService.getAllSchedules();
        System.out.println("Fetched schedules: " + schedules); // Add this log
        model.addAttribute("schedules", schedules);
        return "teachers/schedule";
    }
}
