package org.fastcampus.student_management;

import org.fastcampus.student_management.application.course.CourseService;
import org.fastcampus.student_management.application.course.dto.CourseInfoDto;
import org.fastcampus.student_management.application.student.StudentService;
import org.fastcampus.student_management.application.student.dto.StudentInfoDto;
import org.fastcampus.student_management.repo.CourseCommandRepositoryimpl;
import org.fastcampus.student_management.repo.CourseJdbcCommandRepository;
import org.fastcampus.student_management.repo.StudentRepository;
import org.fastcampus.student_management.ui.course.CourseController;
import org.fastcampus.student_management.ui.course.CoursePresenter;
import org.fastcampus.student_management.ui.student.StudentController;
import org.fastcampus.student_management.ui.student.StudentPresenter;
import org.fastcampus.student_management.ui.UserInputType;

public class Main {

  public static void main(String[] args) {
    StudentRepository studentRepository = new StudentRepository();
    CourseCommandRepositoryimpl courseRepository = new CourseCommandRepositoryimpl();

    CourseJdbcCommandRepository courseJdbcCommandRepository = new CourseJdbcCommandRepository();

    StudentService studentService = new StudentService(studentRepository);
    CourseService courseService = new CourseService(courseRepository, courseJdbcCommandRepository,
        studentRepository);

    CoursePresenter coursePresenter = new CoursePresenter();
    StudentPresenter studentPresenter = new StudentPresenter();

    CourseController courseController = new CourseController(coursePresenter, courseService, studentPresenter);
    StudentController studentController = new StudentController(studentPresenter, studentService);

    StudentInfoDto mockup1 = new StudentInfoDto("대훈", 20, "서울");
    StudentInfoDto mockup2 = new StudentInfoDto("도훈", 15, "부산");
    studentService.saveStudent(mockup2);
    studentService.saveStudent(mockup1);


    CourseInfoDto courseInfoDto = new CourseInfoDto("Math", 20000, "MONDAY", "대훈", 1500L);
    CourseInfoDto courseInfoDto2 = new CourseInfoDto("Math", 20000, "MONDAY", "도훈", 1500L);
    courseService.registerCourse(courseInfoDto);
    courseService.registerCourse(courseInfoDto2);


    studentPresenter.showMenu();
    UserInputType userInputType = studentController.getUserInput();
    while (userInputType != UserInputType.EXIT) {
      switch (userInputType) {
        case NEW_STUDENT:
          studentController.registerStudent();
          break;
        case NEW_COURSE:
          courseController.registerCourse();
          break;
        case SHOW_COURSE_DAY_OF_WEEK:
          courseController.showCourseDayOfWeek();
          break;
        case ACTIVATE_STUDENT:
          studentController.activateStudent();
          break;
        case DEACTIVATE_STUDENT:
          studentController.deactivateStudent();
          break;
        case CHANGE_FEE:
          courseController.changeFee();
          break;
        default:
          studentPresenter.showErrorMessage();
          break;
      }
      studentPresenter.showMenu();
      userInputType = studentController.getUserInput();
    }
  }
}