package com.eduportal.core.ClassConnect.Admin.AdminRepo;


import com.eduportal.core.ClassConnect.Admin.AdminModel.AdminLoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminLoginRepo extends JpaRepository<AdminLoginModel , Long> {

}
