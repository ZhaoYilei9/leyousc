package com.leyou.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leyou.common.PageResult;
import com.leyou.mapper.BrandMapper;
import com.leyou.mapper.SpuMapper;
import com.leyou.pojo.Brand;
import com.leyou.pojo.Spu;
import com.leyou.pojo.SpuBo;
import com.leyou.service.CategoryService;
import com.leyou.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("spuService")
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageResult<SpuBo> queryForSpuList(String key, Integer page, Integer rows, String sortBy, Boolean desc,Boolean saleable) {

        PageHelper.startPage(page,rows);
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if (saleable!= null){

            criteria.orEqualTo("saleable",saleable);
        }
        if (StringUtils.isNotBlank(key)){
            criteria.andLike("title" , "%" + key + "%");
        }
        if (StringUtils.isNotBlank(sortBy)){
            String sort = sortBy + (desc ? " desc" : " asc");
            example.setOrderByClause(sort);
        }
        Page<Spu> spus = (Page<Spu>)spuMapper.selectByExample(example);
        List<SpuBo> spuBos = spus.stream().map(spu -> {

            List<String> cnames = categoryService.queryForCnameByCids(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            SpuBo spuBo = new SpuBo();
            BeanUtils.copyProperties(spu,spuBo);
            spuBo.setCname(StringUtils.join(cnames, "/"));
            Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());
            spuBo.setBname(brand.getName());
            return spuBo;

        }).collect(Collectors.toList());



        return new PageResult<>(spus.getTotal(),spuBos);
    }
}
