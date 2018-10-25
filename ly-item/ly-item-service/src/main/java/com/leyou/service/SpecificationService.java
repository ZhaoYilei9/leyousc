package com.leyou.service;

import com.leyou.pojo.Specification;

public interface SpecificationService {
    String querySpecificationsByCategoryId(Long cid);
    int addSpec(Specification specification);
    int updateSpec(Specification specification);
}
