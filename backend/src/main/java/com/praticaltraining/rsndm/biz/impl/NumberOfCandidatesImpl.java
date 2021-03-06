package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.NumberOfCandidatesBiz;
import com.praticaltraining.rsndm.entity.NumberOfCandidates;
import com.praticaltraining.rsndm.exception.NumberOfCandidatesException;
import com.praticaltraining.rsndm.mapper.NumberOfCandidatesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumberOfCandidatesImpl implements NumberOfCandidatesBiz {
    @Autowired
    private NumberOfCandidatesMapper numberOfCandidatesMapper;
    @Override
    public NumberOfCandidates numberOfCandidatesGet(int eduId) {
        NumberOfCandidates res=numberOfCandidatesMapper.numberOfCandidatesGet(eduId);
        if(res==null){
            throw new NumberOfCandidatesException("NumberOfCandidates get error");
        }
        return res;
    }

    @Override
    public void NumberOfCandidatesUpdate(NumberOfCandidates numberOfCandidates) {
        int res=numberOfCandidatesMapper.NumberOfCandidatesUpdate(numberOfCandidates);
        if(res==0){
            throw new NumberOfCandidatesException("NumberOfCandidates update error");
        }
    }

    @Override
    public NumberOfCandidates edu2GetNum(int eduId){
        return numberOfCandidatesMapper.edu2GetNum(eduId);
    }

    @Override
    public NumberOfCandidates edu3GetNum(int eduId){
        return numberOfCandidatesMapper.edu3GetNum(eduId);
    }
}
