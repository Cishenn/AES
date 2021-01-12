package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.ViolationRecordBiz;
import com.praticaltraining.rsndm.entity.ViolationRecord;
import com.praticaltraining.rsndm.exception.ViolationRecordException;
import com.praticaltraining.rsndm.mapper.ViolationRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViolationRecordImpl implements ViolationRecordBiz {
    @Autowired
    private ViolationRecordMapper violationRecordMapper;
    @Override
    public List<ViolationRecord> getAllByEsId(int esId) {
        List<ViolationRecord> res=violationRecordMapper.getAllByEsId(esId);
        if(res==null){
            throw new ViolationRecordException("getAll by esId");
        }
        return res;
    }
}
