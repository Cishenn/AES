package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.ExamRoom;
import com.praticaltraining.rsndm.entity.ExamStaff;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExamStaffMapper {
    int register(ExamStaff examStaff);

    String getName(int esId);

    ExamStaff getOneExStaff(int esId);

    int verifyPass(int esId);

    int getEsIdByTele(String telephoneNumber);

    int verifyNotPass(int esId);

    int updateExStaff(ExamStaff examStaff);
}
