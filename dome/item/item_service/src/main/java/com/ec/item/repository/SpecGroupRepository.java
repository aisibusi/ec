package com.ec.item.repository;

import com.lh.ec.item.pojo.SpecGroup;

import java.util.List;

public interface SpecGroupRepository extends BaseRepository<SpecGroup, Long>{
    List<SpecGroup> findByCidIs(Long cid);
}
