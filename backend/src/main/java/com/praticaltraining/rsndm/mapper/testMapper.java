package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface testMapper {
    List<test> getTest();
}
