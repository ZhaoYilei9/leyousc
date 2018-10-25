package com.leyou.controller;

import com.leyou.pojo.Category;
import com.leyou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public ResponseEntity<List<Category>> queryForCategoryList(@RequestParam(value = "pid",defaultValue = "0L") Long pid){
        List<Category> list = categoryService.queryForCategoryList(pid);
        if (list == null || list.size() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryForItemCategorys(@PathVariable("bid") Long bid){

        List<Category> categorys = categoryService.queryForBrandCategorysName(bid);

        return ResponseEntity.status(HttpStatus.OK).body(categorys);

    }
}
