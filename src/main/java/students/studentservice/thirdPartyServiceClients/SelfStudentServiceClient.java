package students.studentservice.thirdPartyServiceClients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import students.studentservice.Exceptions.NotFoundException;
import students.studentservice.models.Student;
import students.studentservice.repositories.StudentRepository;

import java.util.List;
import java.util.UUID;

@Component
public class SelfStudentServiceClient {
    private StudentRepository studentRepository;

    @Autowired
    public SelfStudentServiceClient(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student insertStudent(Student student) {
                return studentRepository.save(student);
    }
    public Student getStudentById(String id) throws NotFoundException {
        UUID uuid = UUID.fromString(id);
        Student student = studentRepository.findById(uuid).orElse(null);
        if(student == null) {
            throw new NotFoundException("The Student with Id " + id + " is not found.");
        }
        return student;
    }
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(String id, Student student) throws NotFoundException {
        UUID uuid = UUID.fromString(id);
        Student existingStudent = studentRepository.findById(uuid).orElse(null);
        if(existingStudent == null)
            throw new NotFoundException("The Student with Id " + id + " is not found.");

        existingStudent.setCGPA(student.getCGPA());
        existingStudent.setBranch(student.getBranch());
        existingStudent.setAge(student.getAge());
        existingStudent.setUniversityName(student.getUniversityName());
        existingStudent.setName(student.getName());
        return studentRepository.save(existingStudent);
    }

    public Student deleteStudent(String id) throws NotFoundException {
        UUID uuid = UUID.fromString(id);
        Student student = studentRepository.findById(uuid).orElse(null);
        if(student == null)
            throw new NotFoundException("The Student with Id " + id + " is not found.");
         studentRepository.delete(student);
         return student;
    }
}
