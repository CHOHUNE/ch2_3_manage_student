package org.fastcampus.student_management.application.course;

import java.util.ArrayList;
import java.util.List;
import org.fastcampus.student_management.application.course.dto.CourseInfoDto;
import org.fastcampus.student_management.application.student.StudentService;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.DayOfWeek;
import org.fastcampus.student_management.domain.Student;
import org.fastcampus.student_management.repo.CourseRepository;

public class CourseService {
  private final CourseRepository courseRepository;
  private final StudentService studentService;

  public CourseService(CourseRepository courseRepository, StudentService studentService) {
    this.courseRepository = courseRepository;
    this.studentService = studentService;
  }

  public void registerCourse(CourseInfoDto courseInfoDto) {
    Student student = studentService.getStudent(courseInfoDto.getStudentName());
    Course course = new Course(student, courseInfoDto.getCourseName(), courseInfoDto.getFee(), courseInfoDto.getDayOfWeek(), courseInfoDto.getCourseTime());
    courseRepository.save(course);
  }

  public List<CourseInfoDto> getCourseDayOfWeek(DayOfWeek dayOfWeek) {

    List<Course> courses = courseRepository.getCourseDayOfWeek(dayOfWeek);
    List<CourseInfoDto> courseInfoDtos = new ArrayList<>();

    for (Course course : courses) {
      CourseInfoDto courseInfoDto = new CourseInfoDto(course);
      courseInfoDtos.add(courseInfoDto);
    }

    return courseInfoDtos;
  }

  public void changeFee(String studentName, int fee) {

    List<Course> courseListByStudent = courseRepository.getCourseListByStudent(studentName);

    for (Course course : courseListByStudent) {
      course.setFee(fee);
    }

    courseRepository.saveCourses(courseListByStudent);

  }
}
