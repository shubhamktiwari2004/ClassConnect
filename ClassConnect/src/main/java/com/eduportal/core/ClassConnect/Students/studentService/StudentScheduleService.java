package com.eduportal.core.ClassConnect.Students.studentService;

import com.eduportal.core.ClassConnect.Students.studentRepository.StudentScheduleRepo;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.ScheduleModel;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentScheduleService {

    private final StudentScheduleRepo studentScheduleRepo;

    public StudentScheduleService(StudentScheduleRepo studentScheduleRepo) {
        this.studentScheduleRepo = studentScheduleRepo;
    }

    public List<ScheduleModel> getTodaySchedules(Integer teacher_id) {
        List<ScheduleModel> schedules = studentScheduleRepo.findByTeacherId(teacher_id);
        LocalDate today = LocalDate.now();
        return schedules.stream()
                .filter(schedule -> schedule.getDate().isEqual(today))
                .collect(Collectors.toList());
    }

    public List<ScheduleModel> getUpcomingSchedules(Integer teacher_id) {
        List<ScheduleModel> schedules = studentScheduleRepo.findByTeacherId(teacher_id);
        LocalDate today = LocalDate.now();
        return schedules.stream()
                .filter(schedule -> schedule.getDate().isAfter(today))
                .collect(Collectors.toList());
    }
}
