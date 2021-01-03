package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.ExamRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamRoomMapper {
    List<ExamRoom> getAllExamRoom(int schoolId);
}
