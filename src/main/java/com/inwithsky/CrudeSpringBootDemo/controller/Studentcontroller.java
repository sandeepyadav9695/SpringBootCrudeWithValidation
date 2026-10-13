package com.inwithsky.CrudeSpringBootDemo.controller;

import com.inwithsky.CrudeSpringBootDemo.dto.CreateStudentRequestDto;
import com.inwithsky.CrudeSpringBootDemo.dto.CreateStudentResponseDto;
import com.inwithsky.CrudeSpringBootDemo.dto.UpdateStudentRequestDto;
import com.inwithsky.CrudeSpringBootDemo.dto.UpdateStudentResponseDto;
import com.inwithsky.CrudeSpringBootDemo.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Students")
public class Studentcontroller {

    private final StudentService studentService;

    public Studentcontroller(StudentService studentService) {
        this.studentService = studentService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<CreateStudentResponseDto> createStudent(
            @Valid @RequestBody CreateStudentRequestDto studentRequestDto) {

        CreateStudentResponseDto createdStudent =
                studentService.createStudent(studentRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    // GET ONE
    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDto> getStudent(
            @PathVariable Long id) {

        CreateStudentResponseDto studentRes =
                studentService.getStudent(id);

        return ResponseEntity.ok(studentRes);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDto>> getAllStudent() {

        List<CreateStudentResponseDto> students =
                studentService.getAllStudent();

        return ResponseEntity.ok(students);
    }

    // UPDATE
    @PutMapping
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(
            @RequestParam Long id,
            @RequestBody UpdateStudentRequestDto studentReq) {

        UpdateStudentResponseDto studentRes =
                studentService.updateStudent(id, studentReq);

        return ResponseEntity.ok(studentRes);
    }

    // HARD DELETE
    @DeleteMapping
    public ResponseEntity<Void> deleteStudent(
            @RequestParam Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    // SOFT DELETE
    @PatchMapping("/delete-soft")
    public ResponseEntity<Void> deleteStudentSoft(
            @RequestParam Long id) {

        studentService.deleteStudentSoft(id);

        return ResponseEntity.noContent().build();
    }
}