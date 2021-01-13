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

    int roomsIsArangeOfOneSchool(int schoolId);

    int floorsIsArange(int eduId);

    int floorsIsArangeOfOneSchool(int schoolId);

    int clearArrange(int schoolId);

    int setArrange(int exRoomId);

    List<ExamRoom> getAllArrangedExamRoom(int schoolId);

    int getSelectedRoom(int schoolId);

    List<Integer> isArrangeFloorId(int schoolId);

    Integer getIdByAll(int floorId,int schoolId,String roomNum);
}
