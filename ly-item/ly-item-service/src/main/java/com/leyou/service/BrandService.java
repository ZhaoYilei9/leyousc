package com.leyou.service;

import com.github.pagehelper.Page;
import com.leyou.common.PageResult;
import com.leyou.pojo.Brand;

import java.util.List;

public interface BrandService {
    PageResult<Brand> queryForBrandList(String key, int page, int rows, String sortBy, boolean desc);
    Long saveBrand(List<Long> cids, Brand brand);
    List<Brand> getBrandsByCid(long cid);
}
