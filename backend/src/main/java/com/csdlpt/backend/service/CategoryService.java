package com.csdlpt.backend.service;

import com.csdlpt.backend.entity.CategoryEntity;
import com.csdlpt.backend.mapper.CategoryMapper;
import com.csdlpt.backend.model.category.CategoryList;
import com.csdlpt.backend.model.category.CategoryReq;
import com.csdlpt.backend.model.category.CategoryRes;
import com.csdlpt.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryMapper mapper;
    private final CategoryRepository repo;

    @Autowired
    public CategoryService(CategoryMapper mapper, CategoryRepository repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    public CategoryList getAllCategory() {
        List<CategoryEntity> entityList = repo.findAll().stream().filter(
                categoryEntity -> categoryEntity.getDeletedAt() == null
        ).collect(Collectors.toList());

        CategoryList list = mapper.mapCategoryListFromCategoryEntities(entityList);

        return list;
    }

    public CategoryRes addCategory(CategoryReq empReq) {
        CategoryEntity empEntity = mapper.mapCategoryEntityFromCategoryReq(empReq);
        CategoryEntity saved = repo.save(empEntity);

        return mapper.mapCategoryResFromCategoryEntity(saved);
    }

    public CategoryRes getCategory(int id) {
        CategoryEntity empEntity = repo.getById(id);

        if(empEntity.getDeletedAt() != null) {
            return new CategoryRes();
        }

        return mapper.mapCategoryResFromCategoryEntity(empEntity);
    }

    public CategoryRes updateCategory(int id, CategoryReq empReq) {
        CategoryEntity empEntity = mapper.mapCategoryEntityFromCategoryReq(id, empReq);
        CategoryEntity saved = repo.save(empEntity);

        return mapper.mapCategoryResFromCategoryEntity(saved);
    }

    public void deleteCategory(int id) {
        CategoryEntity empEntity = repo.getById(id);
        Date current = new Date();

        empEntity.setDeletedAt(current);

        repo.save(empEntity);
    }
}
