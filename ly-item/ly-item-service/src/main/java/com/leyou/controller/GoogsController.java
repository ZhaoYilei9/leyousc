package com.leyou.controller;

import com.leyou.common.PageResult;
import com.leyou.pojo.SpuBo;
import com.leyou.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//spu/page?key=&page=1&sortBy=id&desc=false
@RestController
public class GoogsController {

    @Autowired
    private GoodsService spuService;

    @RequestMapping("/spu/page")
    public ResponseEntity<PageResult<SpuBo>> queryForSpuList(
            @RequestParam("key")String key,
            @RequestParam("page")int page,
            @RequestParam("rows")int rows,
            @RequestParam("sortBy")String sortBy,
            @RequestParam(value = "desc",defaultValue = "false")boolean desc,
            @RequestParam(value = "saleable",defaultValue = "true") boolean saleable
    ){
        PageResult<SpuBo> spus = spuService.queryForSpuList(key,page,rows,sortBy,desc,saleable);
        System.out.println(spus.getItems());
        System.out.println(key);
        if (spus == null || spus.getItems().size() < 1){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }

        return ResponseEntity.status(HttpStatus.OK).body(spus);
    }

}
