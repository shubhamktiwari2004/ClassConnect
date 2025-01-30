package com.eduportal.core.ClassConnect.Admin.AdminService;

import com.eduportal.core.ClassConnect.Admin.AdminRepo.AdminDashboardRepo;
import com.eduportal.core.ClassConnect.Students.studentsModel.StudentLoginModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminDashboardService {

    private final AdminDashboardRepo adminDashboardRepo;

    public AdminDashboardService(AdminDashboardRepo adminDashboardRepo) {
        this.adminDashboardRepo = adminDashboardRepo;
    }

    public ArrayList<StudentLoginModel> getStudentByTeacherId(int id) {
        System.out.println("teacher_id"+id);
        return adminDashboardRepo.getStudentList(id);
    }
}
