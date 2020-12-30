package com.priticaltraining.rsndm.entity;

import lombok.Data;

@Data
public class ViolationRecord {
    int vrId;
    int esId;
    int year;
    String vrMessage;
    String vrPunish;
}
