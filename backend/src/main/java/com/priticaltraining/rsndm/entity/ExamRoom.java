package com.priticaltraining.rsndm.entity;

import lombok.Data;

@Data
public class ExamRoom{
    int exRoomId;
    int schoolId;
    int floorId;
    String roomNum;
    int isArrange;
}