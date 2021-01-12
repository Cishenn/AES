package com.praticaltraining.rsndm.biz;

import com.praticaltraining.rsndm.entity.ViolationRecord;

import java.util.List;

public interface ViolationRecordBiz {
    List<ViolationRecord> getAllByEsId(int esId);
}
