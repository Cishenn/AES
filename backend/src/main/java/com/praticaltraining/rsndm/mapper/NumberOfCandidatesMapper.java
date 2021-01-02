package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.NumberOfCandidates;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NumberOfCandidatesMapper {
    NumberOfCandidates numberOfCandidatesGet(int year,int eduId);
    int NumberOfCandidatesUpdate(NumberOfCandidates numberOfCandidates);
}
