package com.priticaltraining.rsndm.mapper;

import com.priticaltraining.rsndm.entity.test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface testMapper {
    List<test> getTest();
}
