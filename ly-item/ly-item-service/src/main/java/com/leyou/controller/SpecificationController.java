package com.leyou.controller;

import com.leyou.pojo.Specification;
import com.leyou.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    @RequestMapping("{cid}")
    public ResponseEntity<String> querySpecificationsByCategoryId(@PathVariable("cid") long cid){

        String specifications = specificationService.querySpecificationsByCategoryId(cid);
        System.out.println(cid);
        if (specifications == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(specifications);

    }

    @PostMapping
    public ResponseEntity<Integer> addSpec(@RequestParam("categoryId")long categoryId,@RequestParam("specifications")String spec){

        Specification specification = new Specification(categoryId,spec);
        int insertCount = specificationService.addSpec(specification);
        if (insertCount < 1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(insertCount);

    }

    @PutMapping
    public ResponseEntity updateSpec(@RequestParam("categoryId")long categoryId,@RequestParam("specifications")String spec){
        Specification specification = new Specification(categoryId,spec);
        int updateCount = specificationService.updateSpec(specification);
        if (updateCount < 1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(updateCount);

    }


}
