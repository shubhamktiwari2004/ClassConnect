package com.eduportal.core.ClassConnect.Teacher.teachersController;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.ScheduleModel;
import com.eduportal.core.ClassConnect.Teacher.teacherService.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ScheduleController {


    @Autowired
    private ScheduleService scheduleService; // Assuming you have a service class

    @PostMapping("/teachers/schedule/post")
    public String postScheduleForm(@ModelAttribute ScheduleModel scheduleModel, RedirectAttributes redirectAttributes) {
        System.out.println(scheduleModel);
        scheduleService.createSchedule(scheduleModel);
        redirectAttributes.addFlashAttribute("message", "Data inserted successfully!");
        return "redirect:/teachers/schedule";
    }
}
