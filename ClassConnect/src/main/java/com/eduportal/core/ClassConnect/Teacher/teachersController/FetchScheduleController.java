package com.eduportal.core.ClassConnect.Teacher.teachersController;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchScheduleModel;
import com.eduportal.core.ClassConnect.Teacher.teacherService.FetchScheduleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FetchScheduleController {


    @Autowired
    private FetchScheduleService scheduleService;

    @GetMapping("/teachers/schedule")
    public String getAllSchedules(Model model , HttpSession session){
        int teacherId = (int) session.getAttribute("teacher_id");
        List<FetchScheduleModel> schedules = scheduleService.getAllSchedules(teacherId);
        model.addAttribute("schedules", schedules);
        for (FetchScheduleModel schedule : schedules) {
            System.out.println("Schedule Id: " + schedule.getSchedule_id());
            System.out.println("Date: " + schedule.getDate());
            System.out.println("start_time: " + schedule.getStartTime());
            System.out.println("end_time: " + schedule.getEndTime());
            System.out.println("Room Number: " + schedule.getRoomNumber());
            System.out.println("Teacher Id: " + schedule.getTeacherId());
            System.out.println("Topic: " + schedule.getTopic());
        }
        return "teachers/schedule";
    }

    @GetMapping("/teachers/schedule/edit/{schedule_id}")
    public String getScheduleById(@PathVariable int schedule_id , Model model){
        List<FetchScheduleModel> scheduleById = scheduleService.getScheduleById(schedule_id);
        model.addAttribute("scheduleById" ,scheduleById);
        for (FetchScheduleModel schedule : scheduleById) {
            System.out.println("Schedule Id: " + schedule.getSchedule_id());
            System.out.println("Class Name : " + schedule.getClassName());
            System.out.println("Subject: " + schedule.getSubject());
            System.out.println("Date: " + schedule.getDate());
            System.out.println("start_time: " + schedule.getStartTime());
            System.out.println("end_time: " + schedule.getEndTime());
            System.out.println("Room Number: " + schedule.getRoomNumber());
            System.out.println("Teacher Id: " + schedule.getTeacherId());
            System.out.println("Topic: " + schedule.getTopic());
            System.out.println("Notes: " + schedule.getNotes());
        }
        return "teachers/editSchedule";
    }
}
