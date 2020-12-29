package com.priticaltraining.rsndm.entity;

import lombok.Data;

@Data
public class ViolationRecord {
    int vrId;
    int pepId;
    int year;
    String vrMessage;
    String vrPunish;
}
