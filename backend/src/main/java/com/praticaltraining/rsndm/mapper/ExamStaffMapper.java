package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.ExamStaff;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExamStaffMapper {
    int register(ExamStaff examStaff);
}