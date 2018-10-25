package com.leyou.service.impl;

import com.leyou.mapper.CategoryMapper;
import com.leyou.pojo.Category;
import com.leyou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> queryForCategoryList(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        List<Category> list = categoryMapper.select(category);
        return list;
    }

    @Override
    public List<Category> queryForBrandCategorysName(Long bid) {
        List<Long> cids = categoryMapper.queryForBrandCategories(bid);
        List<Category> categorys = new ArrayList<>();
        for (long cid: cids) {
            Category category = categoryMapper.queryCategory(cid);
            categorys.add(category);
        }

        return categorys;
    }

    @Override
    public List<String> queryForCnameByCids(List<Long> cids) {

        List<String> cnames = new ArrayList<>();
        for (Long cid: cids) {
            Category category = categoryMapper.queryCategory(cid);
            String cname = category.getName();
            cnames.add(cname);
        }

        return cnames;
    }
}
