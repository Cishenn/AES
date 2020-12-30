package com.priticaltraining.rsndm.entity;

import lombok.Data;

@Data
public class InvigilatorGroupArrangement{
    int igArrangeId;
    int invigilatorGroupId;
    int schoolId;
    int exRoomId;
    int sessions;
}