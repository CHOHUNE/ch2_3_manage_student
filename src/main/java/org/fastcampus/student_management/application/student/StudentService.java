package org.fastcampus.student_management.application.student;

import java.util.Optional;
import org.fastcampus.student_management.application.student.dto.StudentInfoDto;
import org.fastcampus.student_management.domain.Student;
import org.fastcampus.student_management.repo.StudentRepository;

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void saveStudent(StudentInfoDto studentInfoDto) {
        Student student = new Student(studentInfoDto.getName(), studentInfoDto.getAge(),
            studentInfoDto.getAddress());
        studentRepository.save(student);
    }

    public Student getStudent(String name) {
        return studentRepository.findByName(name)
            .orElseThrow(() -> new IllegalArgumentException("해당하는 학생이 없습니다."));
    }

    public void activateStudent(String name) {

        Optional<Student> student = studentRepository.findByName(name);

        student.ifPresent(student1 -> {
            if (!student1.isActivate()) {
                student1.setActivate(true);
            }
        });

    }

    public void deactivateStudent(String name) {
      Optional<Student> student = studentRepository.findByName(name);

      student.ifPresent( student1 -> {
        if (student1.isActivate()) {
          student1.setActivate(false);
        }
      });
    }
}
