package com.eduportal.core.ClassConnect.Teacher.teacherService;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.ScheduleModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository; // Injecting the repository

    public ScheduleModel createSchedule(ScheduleModel scheduleModel) {
        // You can add any business logic here if needed
        return scheduleRepository.save(scheduleModel); // Save to database
    }
}
