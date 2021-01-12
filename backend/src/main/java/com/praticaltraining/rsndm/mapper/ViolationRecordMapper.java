package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.ViolationRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViolationRecordMapper {
    List<ViolationRecord> getAllByEsId(int esId);
}
