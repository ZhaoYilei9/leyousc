package com.leyou.service;

import com.leyou.pojo.Category;

import java.util.List;

public interface CategoryService {
    List<Category> queryForCategoryList(Long pid);

    List<Category> queryForBrandCategorysName(Long bid);

    List<String> queryForCnameByCids(List<Long> cids);
}
