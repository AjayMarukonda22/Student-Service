package students.studentservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import students.studentservice.Exceptions.NotFoundException;
import students.studentservice.dtos.StudentRequestDto;
import students.studentservice.dtos.StudentResponseDto;
import students.studentservice.models.Student;
import students.studentservice.thirdPartyServiceClients.SelfStudentServiceClient;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class SelfStudentService implements  StudentService{
    private SelfStudentServiceClient selfStudentServiceClient;
    @Autowired
    public SelfStudentService(SelfStudentServiceClient selfStudentServiceClient) {
        this.selfStudentServiceClient = selfStudentServiceClient;
    }
    @Override
    public StudentResponseDto insertStudent(StudentRequestDto studentRequestDto){
        Student student = selfStudentServiceClient.insertStudent(convertStudentRequestDtoToStudent(studentRequestDto));
        return convertStudentToStudentResponseDto(student);
    }
    @Override
    public StudentResponseDto getStudentById(String id) throws NotFoundException {
        Student student = selfStudentServiceClient.getStudentById(id);
        return convertStudentToStudentResponseDto(student);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        List<Student> students = selfStudentServiceClient.getAllStudents();
        List<StudentResponseDto> studentResponseDtoList = new ArrayList<>();
        for(Student student : students) {
            studentResponseDtoList.add(convertStudentToStudentResponseDto(student));
        }
        return studentResponseDtoList;
    }

    @Override
    public StudentResponseDto updateStudent(String id, StudentRequestDto studentRequestDto) throws NotFoundException {
        Student student = selfStudentServiceClient.updateStudent(id, convertStudentRequestDtoToStudent(studentRequestDto));
        return convertStudentToStudentResponseDto(student);
    }

    @Override
    public StudentResponseDto deleteStudent(String id) throws NotFoundException{
        Student student = selfStudentServiceClient.deleteStudent(id);
        return convertStudentToStudentResponseDto(student);
    }
    public Student convertStudentRequestDtoToStudent(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setUniversityName(studentRequestDto.getUniversityName());
        student.setBranch(studentRequestDto.getBranch());
        student.setCGPA(studentRequestDto.getCGPA());
        return student;
    }
    public StudentResponseDto convertStudentToStudentResponseDto(Student student) {
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setId(student.getUuid().toString());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setUniversityName(student.getUniversityName());
        studentResponseDto.setBranch(student.getBranch());
        studentResponseDto.setCGPA(student.getCGPA());
        return studentResponseDto;
    }
}
