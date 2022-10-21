package com.csdlpt.backend.api;

import com.csdlpt.backend.model.category.CategoryList;
import com.csdlpt.backend.model.category.CategoryReq;
import com.csdlpt.backend.model.category.CategoryRes;
import com.csdlpt.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("backend/v1")
public class CategoryController {
    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @RequestMapping(value = "/categories",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<CategoryList> getAllCategory() {
        CategoryList response = service.getAllCategory();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/categories",
            consumes = {"application/json"},
            produces = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<CategoryRes> addCategory(@RequestBody CategoryReq empReq) {
        CategoryRes response = service.addCategory(empReq);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/categories/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<CategoryRes> getCategory(@PathVariable("id") int id) {
        CategoryRes response = service.getCategory(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/categories/{id}",
            consumes = {"application/json"},
            produces = {"application/json"},
            method = RequestMethod.PUT)
    public ResponseEntity<CategoryRes> putCategory(@PathVariable("id") int id, @RequestBody CategoryReq empReq) {
        CategoryRes response = service.updateCategory(id, empReq);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/categories/{id}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    public ResponseEntity<CategoryRes> deleteCategory(@PathVariable("id") int id) {
        service.deleteCategory(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
