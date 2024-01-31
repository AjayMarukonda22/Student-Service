package students.studentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponseDto {
    private String id;
    private String name;
    private int age;
    private String universityName;
    private String Branch;
    private double CGPA;
}
