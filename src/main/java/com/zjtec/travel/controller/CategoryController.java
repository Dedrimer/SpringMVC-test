package com.zjtec.travel.controller;

import com.zjtec.travel.domain.Category;
import com.zjtec.travel.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

  private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

  @Autowired
  private CategoryService categoryService;

  @RequestMapping(value = "/findAll", method = RequestMethod.GET)
  @ResponseBody
  public List<Category> findAll() {
    logger.info("开始查找旅游目录");
    // 调用 CategoryServiceImpl.findAll 方法获取所有产品目录
      return categoryService.findAll();
  }
}
