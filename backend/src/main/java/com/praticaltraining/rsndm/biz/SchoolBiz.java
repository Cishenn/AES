package com.praticaltraining.rsndm.biz;

import com.praticaltraining.rsndm.entity.School;

import java.util.List;

public interface SchoolBiz {
    List<School> getAll();

    void examineExRoom(int schoolId,int exRoomExamine);

    void submitExRoom(int schoolId);
}
