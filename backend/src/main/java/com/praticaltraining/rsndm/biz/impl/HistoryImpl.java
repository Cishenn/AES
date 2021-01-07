package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.HistoryBiz;
import com.praticaltraining.rsndm.entity.History;
import com.praticaltraining.rsndm.exception.FloorException;
import com.praticaltraining.rsndm.exception.HistoryException;
import com.praticaltraining.rsndm.mapper.HistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryImpl implements HistoryBiz {
    @Autowired
    private HistoryMapper historyMapper;
    @Override
    public List<History> getEsHistory(int esId) {
        List<History> res = historyMapper.getEsHistory(esId);
        if(res==null){
            throw new HistoryException("get History error");
        }
        return res;
    }
}
