package com.leyou.service;

import com.github.pagehelper.Page;
import com.leyou.common.PageResult;
import com.leyou.pojo.Spu;
import com.leyou.pojo.SpuBo;

import java.util.List;

public interface GoodsService {
    PageResult<SpuBo> queryForSpuList(String key, Integer page, Integer rows, String sortBy, Boolean desc,Boolean saleable);
}
