package com.eduportal.core.ClassConnect.Admin.AdminService;

import com.eduportal.core.ClassConnect.Admin.AdminModel.AdminTeacherModel;
import com.eduportal.core.ClassConnect.Admin.AdminRepo.AdminTeacherRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminTeacherService {

    private final AdminTeacherRepo adminTeacherRepo;

    public AdminTeacherService(AdminTeacherRepo adminTeacherRepo) {
        this.adminTeacherRepo = adminTeacherRepo;
    }

    public ArrayList<AdminTeacherModel> getTeachersList(){
        return adminTeacherRepo.getTeacherList();
    }
}
