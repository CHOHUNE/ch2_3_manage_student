package org.fastcampus.student_management.application.course;

import java.util.List;
import org.fastcampus.student_management.application.course.dto.CourseInfoDto;
import org.fastcampus.student_management.application.course.interfaces.CourseCommandRepository;
import org.fastcampus.student_management.application.course.interfaces.CourseQueryRepository;
import org.fastcampus.student_management.application.student.StudentService;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.CourseList;
import org.fastcampus.student_management.domain.DayOfWeek;
import org.fastcampus.student_management.domain.Student;
import org.fastcampus.student_management.repo.StudentRepository;

public class CourseService {
  private final CourseCommandRepository courseCommandRepository;
  private final CourseQueryRepository courseQueryRepository;
  private final StudentRepository studentRepository;

  public CourseService(CourseCommandRepository courseCommandRepository,
      CourseQueryRepository courseQueryRepository, StudentRepository studentRepository) {
    this.courseCommandRepository = courseCommandRepository;
      this.studentRepository = studentRepository;
      this.courseQueryRepository = courseQueryRepository;
  }

  public void registerCourse(CourseInfoDto courseInfoDto) {

    Student student = studentRepository.getStudent(courseInfoDto.getStudentName());
    Course course = new Course(student, courseInfoDto);
    courseCommandRepository.save(course);
  }

  public List<CourseInfoDto> getCourseDayOfWeek(DayOfWeek dayOfWeek) {

    List<Course> courses = courseQueryRepository.getCourseDayOfWeek(dayOfWeek);
    return courses.stream().map(CourseInfoDto::new).toList();
    // Lambda를 이용해 코드 축약이 가능하다.
    // 가독성을 위해 필히 연습 해둘 것

  }

  public void changeFee(String studentName, int fee) {

    List<Course> courseListByStudent = courseQueryRepository.getCourseListByStudent(studentName);
    CourseList courseList = new CourseList(courseListByStudent);
    courseList.changeCoursesFee(fee);

    // changeFee 하는 logic 을 capsule 화 하여 외부에서 파악하지 못하게 하고 가독성을 높인다.
    // 위와 같이 책임소재를 분리 ( 일급 컬렉션 )할 경우 test가 굉장히 용이해진다.
    // 지금은 한가지 logic 이지만 service가 고도화 될 수록 위와 같이 일급 컬렉션의 중요도가 높아진다.


  }
}
