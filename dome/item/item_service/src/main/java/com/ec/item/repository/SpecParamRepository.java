package com.ec.item.repository;

import com.lh.ec.item.pojo.SpecParam;

import java.util.List;

public interface SpecParamRepository extends BaseRepository<SpecParam,Long> {

    List<SpecParam> findByGroupIdIs(Long gid);
}
