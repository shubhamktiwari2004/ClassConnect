package com.eduportal.core.ClassConnect.Students.studentsModel;

import jakarta.persistence.*;

@Entity
@Table(name = "student_details")
public class StudentLoginModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String address;
    String email;
    String firstName;
    String lastName;
    String image_url;
    String is_active;
    String password;
    Long student_id;
    int teacher_id;
    String username;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "StudentLoginModel{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", image_url='" + image_url + '\'' +
                ", is_active='" + is_active + '\'' +
                ", password='" + password + '\'' +
                ", student_id='" + student_id + '\'' +
                ", teacher_id='" + teacher_id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
