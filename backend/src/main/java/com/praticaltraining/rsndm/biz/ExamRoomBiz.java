package com.praticaltraining.rsndm.biz;

import com.praticaltraining.rsndm.entity.ExamRoom;

import java.util.List;

public interface ExamRoomBiz {
    List<ExamRoom> getAllExamRoom(int schoolId);
}
