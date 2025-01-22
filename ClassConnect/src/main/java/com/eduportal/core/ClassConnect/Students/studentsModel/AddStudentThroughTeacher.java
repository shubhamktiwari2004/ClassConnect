package com.eduportal.core.ClassConnect.Students.studentsModel;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "students") // Database table name for the target table
public class AddStudentThroughTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long student_id;
    private Integer teacher_id;
    private String email;
    private String username;


    private String password;
    private String is_active;

    private String session_id;
    private LocalDateTime login_stamp;
    private LocalDateTime logout_stamp;
    private String role;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public LocalDateTime getLogin_stamp() {
        return login_stamp;
    }

    public void setLogin_stamp(LocalDateTime login_stamp) {
        this.login_stamp = login_stamp;
    }

    public LocalDateTime getLogout_stamp() {
        return logout_stamp;
    }

    public void setLogout_stamp(LocalDateTime logout_stamp) {
        this.logout_stamp = logout_stamp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
