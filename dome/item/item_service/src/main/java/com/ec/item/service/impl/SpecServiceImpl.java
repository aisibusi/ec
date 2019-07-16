package com.ec.item.service.impl;

import com.ec.item.repository.SpecGroupRepository;
import com.ec.item.repository.SpecParamRepository;
import com.ec.item.service.SpecService;
import com.lh.ec.common.enums.ExceptionEnum;
import com.lh.ec.common.exception.EcException;
import com.lh.ec.item.pojo.SpecGroup;
import com.lh.ec.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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
}
