package com.eduportal.core.ClassConnect.Students.studentsController;

import com.eduportal.core.ClassConnect.Students.studentService.StudentScheduleService;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.ScheduleModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class StudentScheduleController {

    private final StudentScheduleService studentScheduleService;

    public StudentScheduleController(StudentScheduleService studentScheduleService) {
        this.studentScheduleService = studentScheduleService;
    }

    @GetMapping("students/schedule")
    public String getStudentSchedule(Model model, HttpSession session) {
        Integer teacher_id = (Integer) session.getAttribute("teacher_id");

        // Get today's and upcoming schedules
        List<ScheduleModel> todayDate = studentScheduleService.getTodaySchedules(teacher_id);
        List<ScheduleModel> upcomingDate = studentScheduleService.getUpcomingSchedules(teacher_id);

        // Add to the model
        model.addAttribute("today_date", todayDate);
        model.addAttribute("upcoming_date", upcomingDate);

        // Return the view name
        return "students/schedule";  // Thymeleaf template name
    }
}
