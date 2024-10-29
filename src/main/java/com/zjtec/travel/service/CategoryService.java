package com.zjtec.travel.service;

import com.zjtec.travel.domain.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 完成查找所有产品目录功能
     * @return
     */
    List<Category> findAll();
}
