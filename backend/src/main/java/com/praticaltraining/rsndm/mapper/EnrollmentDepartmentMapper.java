package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.EnrollmentDepartment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EnrollmentDepartmentMapper {
    List<Integer> eduIdAllBelong(int eduId);

    EnrollmentDepartment getOne(int eduId);
}
