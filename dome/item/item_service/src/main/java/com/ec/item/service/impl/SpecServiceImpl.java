package com.ec.item.service.impl;

import com.ec.item.repository.SpecGroupRepository;
import com.ec.item.repository.SpecParamRepository;
import com.ec.item.service.SpecService;
import com.lh.ec.common.enums.ExceptionEnum;
import com.lh.ec.common.exception.EcException;
import com.lh.ec.item.pojo.SpecGroup;
import com.lh.ec.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecGroupRepository specGroupRepository;

    @Autowired
    private SpecParamRepository specParamRepository;

    @Override
    public List<SpecGroup> findByCId(Long cid) {

        List<SpecGroup> specGrps = specGroupRepository.findByCidIs(cid);

        if (CollectionUtils.isEmpty(specGrps)) {
            throw new EcException(ExceptionEnum.SPEC_GROUP_NOT_FOUND);
        }
        return specGrps;
    }

    @Override
    public List<SpecParam> querySpecParams(Long gid, Long cid, Boolean searching, Boolean generic) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        specParam.setCid(cid);
        specParam.setSearching(searching);
        specParam.setGeneric(generic);
        Example<SpecParam> example = Example.of(specParam);
        List<SpecParam> specParamList = specParamRepository.findAll(example);
        if (CollectionUtils.isEmpty(specParamList)) {
            throw new EcException(ExceptionEnum.SPEC_PARAM_NOT_FOUND);
        }
        return specParamList;
    }

    @Override
    public List<SpecParam> findParamByGId(Long gid) {
        List<SpecParam> specParams = specParamRepository.findByGroupIdIs(gid);

        if (CollectionUtils.isEmpty(specParams)) {
            throw new EcException(ExceptionEnum.SPEC_PARAMS_NOT_FOUND);
        }
        return specParams;
    }

    @Override
    public void saveSpecParam(SpecParam specParam) {
        try {
            specParamRepository.save(specParam);
        } catch (Exception e) {
            throw new EcException(ExceptionEnum.FAIL_TO_SAVE_SPECPARAM);
        }
    }

    @Override
    public void deleteSpecParam(Long id) {
        try {
            specParamRepository.deleteById(id);
        } catch (Exception e) {
            throw new EcException(ExceptionEnum.FAIL_TO_DELETE_SPECPARAM);
        }
    }

    @Override
    public List<SpecGroup> findSpecsByCid(Long cid) {
        List<SpecGroup> specGroups = findByCId(cid);

        List<SpecParam> specParams = findParamByGId(cid);

        Map<Long, List<SpecParam>> map = new HashMap<>();
        //遍历specParams
        for (SpecParam param : specParams) {
            Long groupId = param.getGroupId();
            if (!map.keySet().contains(param.getGroupId())) {
                //map中key不包含这个组ID
                map.put(param.getGroupId(), new ArrayList<>());
            }
            //添加进map中
            map.get(param.getGroupId()).add(param);
        }

        for (SpecGroup specGroup : specGroups) {
            specGroup.setParams(map.get(specGroup.getId()));
        }

        return specGroups;
    }
}
