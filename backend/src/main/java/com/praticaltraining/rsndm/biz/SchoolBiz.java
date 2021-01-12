package com.praticaltraining.rsndm.biz;

import com.praticaltraining.rsndm.entity.School;

import java.util.List;

public interface SchoolBiz {
    List<School> getAll();

    List<School> getByEduId(int eduId);

    void examineExRoom(int schoolId,int exRoomExamine);

    void submitExRoom(int schoolId);

    String getSchoolName(int schoolId);

    void setType(int schoolId,String typeOfExaminationSite);

    int getState(int schoolId);

    List<School> getArrangedSchool(int eduId);
}
