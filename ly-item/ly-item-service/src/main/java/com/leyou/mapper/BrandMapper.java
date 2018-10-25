package com.leyou.mapper;

import com.leyou.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BrandMapper extends Mapper<Brand> {

    @Insert("insert into tb_category_brand(category_id,brand_id) values ( #{cid},#{bid})")
    void insertBrandCategory(@Param("cid")Long cid , @Param("bid") long bid);
    @Select(" select * from tb_brand where id in (select brand_id from tb_category_brand where category_id = #{cid})")
    List<Brand> getBrandsByCid(@Param("cid")long cid);

}
