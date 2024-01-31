package students.studentservice.services;

import students.studentservice.Exceptions.NotFoundException;
import students.studentservice.dtos.StudentRequestDto;
import students.studentservice.dtos.StudentResponseDto;

import java.util.List;

public interface StudentService {
    StudentResponseDto insertStudent(StudentRequestDto studentRequestDto);
     StudentResponseDto getStudentById(String id) throws NotFoundException;
     List<StudentResponseDto> getAllStudents();
     StudentResponseDto updateStudent(String id, StudentRequestDto studentRequestDto) throws NotFoundException;
     StudentResponseDto deleteStudent(String id) throws NotFoundException;
}
