package com.leyou.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.PageResult;
import com.leyou.mapper.BrandMapper;
import com.leyou.pojo.Brand;
import com.leyou.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service("brandService")
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;
    @Override
    public PageResult<Brand> queryForBrandList(String key, int page, int rows, String sortBy, boolean desc) {


        PageHelper.startPage(page,rows);
        Example example = new Example(Brand.class);
        if(!StringUtils.isEmpty(key)){
            example.createCriteria().andLike("name","%" + key + "%").orEqualTo("letter",key);

        }
        if (!StringUtils.isEmpty(sortBy)){
            String sort = sortBy + (desc ? " desc" : " asc");
            example.setOrderByClause(sort);
        }
        Page<Brand> brandList = (Page<Brand>)brandMapper.selectByExample(example);

        return new PageResult<Brand>(brandList.getTotal(),brandList);
    }

    @Override
    @Transactional
    public Long saveBrand(List<Long> cids, Brand brand) {

        try {
            long insertCount = brandMapper.insertSelective(brand);

            //插入数据后数据库已自动为其设置主键，赞！
            //        System.out.println(brand.getId());

            System.out.println(brand.getName());

            for (Long cid : cids) {
                brandMapper.insertBrandCategory(cid, brand.getId());

            }
            return insertCount;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Brand> getBrandsByCid(long cid) {
        List<Brand> brandsByCid = brandMapper.getBrandsByCid(cid);
        return brandsByCid;
    }
}
