package com.eduportal.core.ClassConnect.Teacher.teachersController;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.FetchScheduleModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.ScheduleModel;
import com.eduportal.core.ClassConnect.Teacher.teacherModel.UpdateScheduleModel;
import com.eduportal.core.ClassConnect.Teacher.teacherService.FetchScheduleService;
import com.eduportal.core.ClassConnect.Teacher.teacherService.UpdateScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class UpdateScheduleController {

    @Autowired
    private UpdateScheduleService updateScheduleService;


    @PostMapping("/teachers/schedule/editPost")
    public String postScheduleForm(
            @RequestParam int schedule_id,
            @RequestParam String className,
            @RequestParam String subject,
            @RequestParam LocalDate date,
            @RequestParam LocalTime startTime,
            @RequestParam LocalTime endTime,
            @RequestParam String topic,
            @RequestParam String roomNumber,
            @RequestParam String notes,
            RedirectAttributes redirectAttributes) {

        UpdateScheduleModel updateScheduleModel = new UpdateScheduleModel(schedule_id, className, subject, date,  startTime,  endTime,  topic,  roomNumber,  notes);
        updateScheduleModel.setSchedule_id(schedule_id);
        updateScheduleModel.setClassName(className);
        updateScheduleModel.setSubject(subject);
        updateScheduleModel.setDate(date);
        updateScheduleModel.setStartTime(startTime);
        updateScheduleModel.setEndTime(endTime);
        updateScheduleModel.setTopic(topic);
        updateScheduleModel.setRoomNumber(roomNumber);
        updateScheduleModel.setNotes(notes);


        updateScheduleService.updateSchedule(updateScheduleModel);
        redirectAttributes.addFlashAttribute("message", "Data Updated successfully!");
        return "redirect:/teachers/schedule";
    }


}
