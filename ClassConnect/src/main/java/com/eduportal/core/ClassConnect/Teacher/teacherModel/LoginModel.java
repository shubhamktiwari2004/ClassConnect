package com.eduportal.core.ClassConnect.Teacher.teacherModel;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "teacher_user")
public class LoginModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private int teacher_id;


    private String username;

    private String password;

    private String session_id;

    private String email;



    private String tokenNo;

    private Boolean isActive = true;

    private int isDeleted = 0;

    private LocalDateTime loginTimestamp;
    private LocalDateTime logoutTimestamp;

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public int getDeleted() {
        return isDeleted;
    }

    public void setDeleted(int deleted) {
        isDeleted = deleted;
    }


    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public LocalDateTime getLoginTimestamp() { return loginTimestamp; }
    public void setLoginTimestamp(LocalDateTime loginTimestamp) { this.loginTimestamp = loginTimestamp; }

    public LocalDateTime getLogoutTimestamp() { return logoutTimestamp; }
    public void setLogoutTimestamp(LocalDateTime logoutTimestamp) { this.logoutTimestamp = logoutTimestamp; }


    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

}
