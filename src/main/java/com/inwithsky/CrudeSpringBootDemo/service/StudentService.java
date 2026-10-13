package com.inwithsky.CrudeSpringBootDemo.service;

import com.inwithsky.CrudeSpringBootDemo.dto.CreateStudentRequestDto;
import com.inwithsky.CrudeSpringBootDemo.dto.CreateStudentResponseDto;
import com.inwithsky.CrudeSpringBootDemo.dto.UpdateStudentRequestDto;
import com.inwithsky.CrudeSpringBootDemo.dto.UpdateStudentResponseDto;
import com.inwithsky.CrudeSpringBootDemo.entity.Student;
import com.inwithsky.CrudeSpringBootDemo.exception.DuplicateResourceException;
import com.inwithsky.CrudeSpringBootDemo.exception.ResourceNotFoundException;
import com.inwithsky.CrudeSpringBootDemo.repositry.StudentRepositry;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepositry studentRepositry;

    public StudentService(StudentRepositry studentRepositry) {
        this.studentRepositry = studentRepositry;
    }

    // CREATE
    public CreateStudentResponseDto createStudent(
            CreateStudentRequestDto studentRequestDto) {

        Student student = mapToEntity(studentRequestDto);

        if (emailExist(student)) {
            throw new DuplicateResourceException(
                    "Student with email " + student.getEmail()
                            + " already exists"
            );
        }

        LocalDateTime now = LocalDateTime.now();

        student.setCreateAt(now);
        student.setUpdated(now);

        Student studentResp = studentRepositry.save(student);

        return mapToDto(studentResp);
    }

    // GET ONE
    public CreateStudentResponseDto getStudent(Long id) {

        Student studentRes = studentRepositry
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student with id " + id + " not found"
                        )
                );

        return mapToDto(studentRes);
    }

    // GET ALL
    public List<CreateStudentResponseDto> getAllStudent() {

        List<Student> studentList =
                studentRepositry.findByDeletedIsFalse();

        return studentList.stream()
                .map(this::mapToDto)
                .toList();
    }

    // UPDATE
    public UpdateStudentResponseDto updateStudent(
            Long id,
            UpdateStudentRequestDto studentReq) {

        Student existingStudent = studentRepositry
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student with id " + id + " not found"
                        )
                );

        existingStudent.setName(studentReq.getName());
        existingStudent.setAge(studentReq.getAge());
        existingStudent.setRollno(studentReq.getRollno());
        existingStudent.setSubject(studentReq.getSubject());

        existingStudent.setDeleted(false);
        existingStudent.setUpdated(LocalDateTime.now());

        Student saveStudent =
                studentRepositry.save(existingStudent);

        return mapToUpdateDto(saveStudent);
    }

    // HARD DELETE
    public void deleteStudent(Long id) {

        Student studentToBeDeleted = studentRepositry
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student with id " + id + " not found"
                        )
                );

        studentRepositry.delete(studentToBeDeleted);
    }

    // SOFT DELETE
    public void deleteStudentSoft(Long id) {

        Student studentToBeDeleted = studentRepositry
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student with id " + id + " not found"
                        )
                );

        studentToBeDeleted.setDeleted(true);
        studentToBeDeleted.setUpdated(LocalDateTime.now());

        studentRepositry.save(studentToBeDeleted);
    }

    // DTO → Entity
    private Student mapToEntity(
            CreateStudentRequestDto studentRequestDto) {

        Student student = new Student();

        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setEmail(studentRequestDto.getEmail());
        student.setRollno(studentRequestDto.getRollno());
        student.setSubject(studentRequestDto.getSubject());
        student.setDeleted(false);

        return student;
    }

    // Entity → Create DTO
    private CreateStudentResponseDto mapToDto(Student student) {

        CreateStudentResponseDto studentResponseDto =
                new CreateStudentResponseDto();

        studentResponseDto.setId(student.getId());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setRollno(student.getRollno());
        studentResponseDto.setSubject(student.getSubject());

        studentResponseDto.setMessage("Student Save Successfully");

        studentResponseDto.setCreateAt(student.getCreateAt());
        studentResponseDto.setUpdated(student.getUpdated());

        return studentResponseDto;
    }

    // Entity → Update DTO
    private UpdateStudentResponseDto mapToUpdateDto(
            Student student) {

        UpdateStudentResponseDto studentResponseDto =
                new UpdateStudentResponseDto();

        studentResponseDto.setId(student.getId());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setRollno(student.getRollno());
        studentResponseDto.setSubject(student.getSubject());

        studentResponseDto.setMessage(
                "Student Update Successfully"
        );

        studentResponseDto.setUpdated(student.getUpdated());

        return studentResponseDto;
    }

    // Check duplicate email
    private boolean emailExist(Student student) {
        return studentRepositry.existsByEmail(student.getEmail());
    }
}