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

    @Override
    public void createExRoom(ExamRoom examRoom){
        int res = examRoomMapper.createExRoom(examRoom);
        if(res == 0){
            throw new ExamRoomException("create examRoom error");
        }
    }

    @Override
    public int getNumberOfExRoom(int schoolId){
        int res = examRoomMapper.getNumberOfExRoom(schoolId);
        if(res == -1){
            throw new ExamRoomException("get number of examRoom error");
        }
        return res;
    }

    @Override
    public ExamRoom getOneExRoom(int exRoomId){
        ExamRoom res = examRoomMapper.getOneExRoom(exRoomId);
        if(res == null){
            throw new ExamRoomException("get one ExamRoom error");
        }
        return res;
    }

    @Override
    public void changeExRoom(ExamRoom examRoom){
        int res = examRoomMapper.changeExRoom(examRoom);
        if(res == 0){
            throw new ExamRoomException("update ExamRoom error");
        }
    }

    @Override
    public void deleteExRoom(int exRoomId){
        int res = examRoomMapper.deleteExRoom(exRoomId);
        if(res == 0){
            throw new ExamRoomException("delete ExamRoom error");
        }
    }

    @Override
    public void clearArrange(int schoolId){
        int res = examRoomMapper.clearArrange(schoolId);
        if(res == 0){
            throw new ExamRoomException("clear arrange error");
        }
    }

    @Override
    public void setArrange(int exRoomId){
        int res = examRoomMapper.setArrange(exRoomId);
        if(res == 0){
            throw new ExamRoomException("set arrange error");
        }
    }
}
