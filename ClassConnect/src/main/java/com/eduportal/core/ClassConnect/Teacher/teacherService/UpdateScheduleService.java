package com.eduportal.core.ClassConnect.Teacher.teacherService;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.UpdateScheduleModel;
import com.eduportal.core.ClassConnect.Teacher.teacherRepository.UpdateScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateScheduleService {

    @Autowired
    UpdateScheduleRepository updateScheduleRepository;

    public void updateSchedule(UpdateScheduleModel updateScheduleModel) {
        updateScheduleRepository.updateScheduleById(
                updateScheduleModel.getSchedule_id(),
                updateScheduleModel.getClassName(),
                updateScheduleModel.getSubject(),
                updateScheduleModel.getDate(),
                updateScheduleModel.getStartTime(),
                updateScheduleModel.getEndTime(),
                updateScheduleModel.getTopic(),
                updateScheduleModel.getRoomNumber(),
                updateScheduleModel.getNotes()
        );
    }
}
