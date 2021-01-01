package com.praticaltraining.rsndm.biz;

import com.praticaltraining.rsndm.entity.TestPersonnelLogin;

public interface TestPersonnelLoginBiz {
    String getPwd(String telephoneNumber);
    void register(String telephoneNumber,String password);
}
