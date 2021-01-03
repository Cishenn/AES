package com.praticaltraining.rsndm.entity;

import lombok.Data;

@Data
public class floor_exRoom {
    public Floor floor;
    public ExamRoom examRoom;

    public floor_exRoom(Floor floor,ExamRoom examRoom){
        this.floor = floor;
        this.examRoom = examRoom;
    }
}
