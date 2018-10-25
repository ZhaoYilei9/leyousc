package com.leyou.controller;

import com.github.pagehelper.Page;
import com.leyou.common.PageResult;
import com.leyou.pojo.Brand;
import com.leyou.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping("list")
    public ResponseEntity<PageResult<Brand>> queryForBrandList(
            @RequestParam(value = "key" ,required = false) String key,
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "rows",defaultValue = "5") int rows,
            @RequestParam(value = "sortBy" ,required = false) String sortBy,
            @RequestParam(value = "desc",defaultValue = "false",required = false )boolean desc
    ){

        PageResult<Brand> brandList = brandService.queryForBrandList(key, page, rows, sortBy, desc);
        if (brandList == null || brandList.getItems().size() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }



        return ResponseEntity.status(HttpStatus.OK).body(brandList);
    }

    @PostMapping
    public ResponseEntity<Long> saveBrand(@RequestParam("cids") List<Long> cids, Brand brand){

        long insertCount = brandService.saveBrand(cids,brand);
        if (insertCount < 1 ){
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(insertCount);


    }
    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> getBrandsByCid(@PathVariable("cid")Long cid){
        List<Brand> brandsByCid = brandService.getBrandsByCid(cid);
        if (brandsByCid == null || brandsByCid.size() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(brandsByCid);
    }

}
