package com.zjtec.travel.dao;

import com.zjtec.travel.domain.Category;

import java.util.List;

public interface CategoryDao {
    /**
     * 查询所有分类
     * @return
     */
    List<Category> findAll();
}
