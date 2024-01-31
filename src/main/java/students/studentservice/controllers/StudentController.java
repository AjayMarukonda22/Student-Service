package students.studentservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import students.studentservice.Exceptions.NotFoundException;
import students.studentservice.dtos.StudentRequestDto;
import students.studentservice.dtos.StudentResponseDto;
import students.studentservice.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    public StudentResponseDto insertStudent(@RequestBody StudentRequestDto studentRequestDto) {
        return studentService.insertStudent(studentRequestDto);
    }
    @GetMapping("/{id}")
    public StudentResponseDto getStudentById(@PathVariable("id") String id) throws NotFoundException {
        return studentService.getStudentById(id);
    }
    @GetMapping
    public List<StudentResponseDto> getAllStudents() {
        return studentService.getAllStudents();
    }
    @PostMapping("/{id}")
    public StudentResponseDto updateStudent(@PathVariable("id") String id,@RequestBody StudentRequestDto studentRequestDto) throws NotFoundException {
        return studentService.updateStudent(id, studentRequestDto);
    }
    @DeleteMapping("/{id}")
    public StudentResponseDto deleteStudent(@PathVariable("id") String id) throws NotFoundException {
        return studentService.deleteStudent(id);
    }
}
