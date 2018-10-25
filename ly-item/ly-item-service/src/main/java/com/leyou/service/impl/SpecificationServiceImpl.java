package com.leyou.service.impl;

import com.leyou.mapper.SpecificationMapper;
import com.leyou.pojo.Specification;
import com.leyou.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("specificationService")
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;

    @Override
    public String querySpecificationsByCategoryId(Long cid) {
        Specification specification = specificationMapper.selectByPrimaryKey(cid);
        if (specification == null){
            return null;
        }
        return specification.getSpecifications();
    }

    @Override
    public int addSpec(Specification specification) {
        int insertCount = specificationMapper.insert(specification);
        return insertCount;
    }

    @Override
    public int updateSpec(Specification specification) {

        int updateCount = specificationMapper.updateByPrimaryKey(specification);

        return updateCount;
    }
}
