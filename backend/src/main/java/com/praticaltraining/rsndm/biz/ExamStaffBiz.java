package com.praticaltraining.rsndm.biz;

import com.praticaltraining.rsndm.entity.ExamStaff;

public interface ExamStaffBiz {
    void register(ExamStaff examStaff);
    String getName(int esId);
    ExamStaff getOneExamStaff(int esId);
    void verifyPass(int esId);
    int getEsIdByTele(String TelephoneNumber);
}
