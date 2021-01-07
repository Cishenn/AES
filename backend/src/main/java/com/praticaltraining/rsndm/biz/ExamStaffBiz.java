package com.praticaltraining.rsndm.biz;

import com.praticaltraining.rsndm.entity.ExamRoom;
import com.praticaltraining.rsndm.entity.ExamStaff;

import java.util.List;

public interface ExamStaffBiz {
    void register(ExamStaff examStaff);

    String getName(int esId);

    ExamStaff getOneExamStaff(int esId);

    void verifyPass(int esId);

    void verifyNotPass(int esId);

    int getEsIdByTele(String TelephoneNumber);

    void updateExStaff(ExamStaff examStaff);

    List<ExamStaff> getExStaffListBySchoolId(int schoolId);

    void updateEduExamine(int esId, int eduExmine);

    List<ExamStaff> getQualifiedExStaffList(int eduId);

    void clearArrange(int eduId);

    void updateToArrange(int esId);

    List<ExamStaff> allNoArrangeGetByEduId(int eduId);

    int getSchoolId(int esId);

    List<ExamStaff> getQualifiedExStaffBySchoolId(int schoolId);
}
