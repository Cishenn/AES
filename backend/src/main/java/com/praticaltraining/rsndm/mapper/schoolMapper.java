package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.School;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface schoolMapper {
    School getAll();
    int examineExRoom(int schoolId,int exRoomExamine);
    int submitExRoom(int schoolId);
}
