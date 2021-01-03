package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.ExamRoomBiz;
import com.praticaltraining.rsndm.entity.ExamRoom;
import com.praticaltraining.rsndm.exception.ExamRoomException;
import com.praticaltraining.rsndm.mapper.ExamRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamRoomImpl implements ExamRoomBiz {

    @Autowired
    private ExamRoomMapper examRoomMapper;

    @Override
    public List<ExamRoom> getAllExamRoom(int schoolId){
        List<ExamRoom> res = examRoomMapper.getAllExamRoom(schoolId);
        if(res == null){
            throw new ExamRoomException("getAll ExamRoom error");
        }
        return res;
    }
}
