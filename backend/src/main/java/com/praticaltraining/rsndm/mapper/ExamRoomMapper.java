package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.ExamRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamRoomMapper {
    List<ExamRoom> getAllExamRoom(int schoolId);

    int createExRoom(ExamRoom examRoom);

    int getNumberOfExRoom(int schoolId);

    ExamRoom getOneExRoom(int exRoomId);

    int changeExRoom(ExamRoom examRoom);

    int deleteExRoom(int exRoomId);

    int clearArrange();

    int roomsIsArangeOfOneSchool(int schoolId);

    int floorsIsArange(int eduId);

    int floorsIsArangeOfOneSchool(int schoolId);


}
