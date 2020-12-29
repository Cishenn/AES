package com.priticaltraining.rsndm.entity;

import lombok.Data;

@Data
public class ExamRoom{
    int exRoomId;
    int schoolId;
    String building;
    String floor;
    String roomNum;
}