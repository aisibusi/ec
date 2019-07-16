package com.ec.item.service;

import com.lh.ec.item.pojo.SpecGroup;
import com.lh.ec.item.pojo.SpecParam;

import java.util.List;

public interface SpecService {

    List<SpecGroup> findByCId(Long cid);

    List<SpecParam> findParamByGId(Long gid);


    void saveSpecParam(SpecParam specParam);

    void deleteSpecParam(Long id);
}

