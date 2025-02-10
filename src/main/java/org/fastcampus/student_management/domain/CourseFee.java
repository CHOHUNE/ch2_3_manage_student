package org.fastcampus.student_management.domain;

public class CourseFee
{

    private int fee;

    public CourseFee(int fee) {
        this.fee = fee;
    }

    public int getFee() {
        return fee;
    }

    public void changeFee(int fee) {
        this.fee = fee;
    }

    private void checkFee(int fee) {
        if (fee < 0) {
            throw new IllegalArgumentException("수강료는 0원 이상이여야 한다.");
        }
    }
}

// 해당 CourseFee 처럼 밸류를 객체로 관리함으로서 다른 객체에서 참조할 때
// fee의 밸류를 손쉽게 정의할 수 있다.
