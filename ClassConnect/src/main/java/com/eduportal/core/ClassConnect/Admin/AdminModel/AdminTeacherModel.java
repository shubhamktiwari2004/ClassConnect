package com.eduportal.core.ClassConnect.Admin.AdminModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="teacher_profile")
public class AdminTeacherModel {

    @Id
    private int id;
    private String name;
    private String email;
    private  Long mobile;
    private String address;
    private Long teacher_id;
    private String profile_image;
    private String username;
    private int is_deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }
}
