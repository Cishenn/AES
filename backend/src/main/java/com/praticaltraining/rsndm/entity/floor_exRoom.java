package com.praticaltraining.rsndm.entity;

import lombok.Data;

@Data
public class floor_exRoom implements Comparable<floor_exRoom>{
    public Floor floor;
    public ExamRoom examRoom;

    public floor_exRoom(Floor floor,ExamRoom examRoom){
        this.floor = floor;
        this.examRoom = examRoom;
    }

    @Override
    public int compareTo(floor_exRoom floor_exRoom){
        return this.floor.getFloorStep()-floor_exRoom.floor.getFloorStep();
    }
}
