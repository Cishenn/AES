package com.praticaltraining.rsndm.biz;

import com.praticaltraining.rsndm.entity.ExamRoom;

import java.util.List;

public interface ExamRoomBiz {
    List<ExamRoom> getAllExamRoom(int schoolId);

    void createExRoom(ExamRoom examRoom);

    int getNumberOfExRoom(int schoolId);

    ExamRoom getOneExRoom(int exRoomId);

    void changeExRoom(ExamRoom examRoom);

    void deleteExRoom(int exRoomId);

    void clearArrange();

    int roomsIsArangeOfOneSchool(int schoolId);

    int floorsIsArange(int eduId);

    int floorsIsArangeOfOneSchool(int schoolId);
}
