package com.eduportal.core.ClassConnect.Admin.AdminRepo;


import com.eduportal.core.ClassConnect.Admin.AdminModel.AdminLoginModel;
import com.eduportal.core.ClassConnect.Students.studentsModel.StudentLoginModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminLoginRepo extends JpaRepository<AdminLoginModel , Long> {


    @Query(value = "SELECT * from admin_user_table WHERE username = ?1 AND is_deleted = 0"  , nativeQuery = true)
    Optional<AdminLoginModel> findByUsername(String username);

    @Query(value = "SELECT * from admin_user_table WHERE email = ?1 AND is_deleted = 0"  , nativeQuery = true)
    Optional<AdminLoginModel> findByEmail(String email);

    @Query(value = "SELECT * from admin_user_table WHERE admin_id = ?1 AND is_deleted = 0"  , nativeQuery = true)
    AdminLoginModel findByAdminId(Long adminId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE admin_user_table SET username = ?2, email = ?3, profile_image = ?4 WHERE admin_id = ?1 AND is_deleted = 0", nativeQuery = true)
    void updateProfile(Long adminId , String username , String email , String imageUrl);


}
