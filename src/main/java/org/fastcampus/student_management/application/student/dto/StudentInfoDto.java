package org.fastcampus.student_management.application.student.dto;

public class StudentInfoDto {

  private final String name;
  private final int age;
  private final String address;
  private Boolean isActivate;


  public StudentInfoDto(String name, int age, String address) {

      this.name = name;
    this.age = age;
    this.address = address;
  }


  public Boolean getActivate() {
    return isActivate;
  }

//  public void setActivate(Boolean activate) {
//    isActivate = activate;
//  }
// Entity -> DTO 를 변환하는 레이어 외에서는 Setter 사용을 가급적 지양해야 한다.
//  코드의 가독성과 캡슐화를 매우 떨어뜨리기 때문

  // setActivate 대신 activate 라는 함수를 작성해본다.


  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public String getAddress() {
    return address;
  }
}
