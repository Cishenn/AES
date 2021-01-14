package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.StatusNotesBiz;
import com.praticaltraining.rsndm.entity.StatusNotes;
import com.praticaltraining.rsndm.entity.ViolationRecord;
import com.praticaltraining.rsndm.exception.StatusNotesException;
import com.praticaltraining.rsndm.exception.ViolationRecordException;
import com.praticaltraining.rsndm.mapper.StatusNotesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusNotesImpl implements StatusNotesBiz {
    @Autowired
    private StatusNotesMapper statusNotesMapper;
    @Override
    public List<StatusNotes> getAllByEsId(int esId) {
        List<StatusNotes> res=statusNotesMapper.getAllByEsId(esId);
        if(res==null){
            throw new StatusNotesException("getAll by esId");
        }
        return res;
    }

    @Override
    public List<StatusNotes> getAllBySchool(int schoolId) {
        List<StatusNotes> res=statusNotesMapper.getAllBySchool(schoolId);
        if(res==null){
            throw new StatusNotesException("getAll by schoolId error");
        }
        return res;
    }
}
