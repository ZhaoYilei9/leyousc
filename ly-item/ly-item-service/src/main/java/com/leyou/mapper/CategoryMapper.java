package com.leyou.mapper;

import com.leyou.pojo.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CategoryMapper extends Mapper<Category> {

    @Select("select category_id from tb_category_brand where brand_id= #{bid}")
    List<Long> queryForBrandCategories(@Param("bid") long bid);

    @Select("select * from tb_category where id = #{cid}")
    Category queryCategory(@Param("cid") long cid);
}
