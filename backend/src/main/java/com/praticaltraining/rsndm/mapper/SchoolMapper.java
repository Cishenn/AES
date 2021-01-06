package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.School;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchoolMapper {
    List<School> getAll();

    List<School> getByEduId(int eduId);

    int examineExRoom(int schoolId,int exRoomExamine);

    int submitExRoom(int schoolId);

    String getSchoolName(int schoolId);

    int getState(int schoolId);
}
