package com.inwithsky.CrudeSpringBootDemo.dto;

import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Value;

public class CreateStudentRequestDto {
    @NotBlank(message = "Name can be not null")
    @Size(min = 2,max = 50,message = "Student name must be within 2 to 50 charcter")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value=18,message = "age not less then 18")
    private int age;

    @NotNull(message = "email is required")
    @Email(message = "student email must be valid")
    private String email;

    @NotNull(message = "Roll number is required")
    private Integer rollno;

    @NotBlank(message = "Subject is required")
    private String subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
