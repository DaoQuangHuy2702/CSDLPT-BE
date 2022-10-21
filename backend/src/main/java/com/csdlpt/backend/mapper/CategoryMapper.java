package com.csdlpt.backend.mapper;

import com.csdlpt.backend.entity.CategoryEntity;
import com.csdlpt.backend.model.category.CategoryList;
import com.csdlpt.backend.model.category.CategoryReq;
import com.csdlpt.backend.model.category.CategoryRes;
import com.csdlpt.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryMapper {
    private final CategoryRepository repo;

    @Autowired
    public CategoryMapper(CategoryRepository repo) {
        this.repo = repo;
    }

    public CategoryRes mapCategoryResFromCategoryEntity(CategoryEntity from) {
        CategoryRes to = new CategoryRes();

        to.setId(from.getId());
        to.setName(from.getName());
        to.setParentId(from.getParentId());
        to.setCreatedAt(from.getCreatedAt());
        to.setUpdatedAt(from.getUpdatedAt());

        return to;
    }

    public CategoryList mapCategoryListFromCategoryEntities(List<CategoryEntity> from) {
        CategoryList to = new CategoryList();

        from.stream().forEach(category -> {
            to.add(mapCategoryResFromCategoryEntity(category));
        });

        return to;
    }

    public CategoryEntity mapCategoryEntityFromCategoryReq(CategoryReq from) {
        CategoryEntity to = new CategoryEntity();

        Date current = new Date();

        to.setName(from.getName());
        to.setParentId(from.getParentId());
        to.setCreatedAt(current);
        to.setUpdatedAt(current);

        return to;
    }

    public CategoryEntity mapCategoryEntityFromCategoryReq(int id, CategoryReq from) {
        CategoryEntity to = repo.getById(id);
        Date current = new Date();

        to.setName(from.getName());
        to.setParentId(from.getParentId());
        to.setUpdatedAt(current);

        return to;
    }
}

