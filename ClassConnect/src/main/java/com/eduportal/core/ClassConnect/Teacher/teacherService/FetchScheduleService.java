package com.eduportal.core.ClassConnect.Teacher.teacherService;

import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchScheduleModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.FetchScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchScheduleService {

    @Autowired
    private FetchScheduleRepository scheduleRepository;

    public List<FetchScheduleModel> getAllSchedules(int teacherId) {
        return scheduleRepository.getAllSchedule(teacherId);
    }

    public List<FetchScheduleModel> getScheduleById(int scheduleId){
        return scheduleRepository.getScheduleById(scheduleId);

    }
}
