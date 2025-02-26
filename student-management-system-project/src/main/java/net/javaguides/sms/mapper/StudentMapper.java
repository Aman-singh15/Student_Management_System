package net.javaguides.sms.mapper;

import net.javaguides.sms.dto.StudentDto;
import net.javaguides.sms.entity.Student;

public class StudentMapper {
    public static StudentDto mapToStudentDto(Student student){
        StudentDto studentDto= new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()

        );
        return studentDto;
    }
    public static Student mapToStudent(StudentDto studentDto){
     //   mapToStudent(StudentDto studentDto): Converts DTO (StudentDto) to entity (Student) before saving to the database.
        Student student=new Student(
                studentDto.getId(),
                studentDto.getFirstName(),
                studentDto.getLastName(),
                studentDto.getEmail()

        );
        return student;
    }
}
