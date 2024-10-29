package com.zjtec.travel.service.impl;

import com.zjtec.travel.controller.RegisterController;
import com.zjtec.travel.dao.CategoryDao;
import com.zjtec.travel.domain.Category;
import com.zjtec.travel.service.CategoryService;
import com.zjtec.travel.util.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> findAll() {
        //1.从redis中查询
        //1.1 获取jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        //1.2 可使用sortedset排序查询
        //Set<String> categorys = jedis.zrange("category", 0, -1);
        //1.3 查询sortedset中的分数（cid）和值（cname）
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);

        List<Category> cs = null;
        //2.判断查询的集合是否为空
        if (categorys == null || categorys.size() == 0){
            logger.info("从数据库查询....");
            //3. 如果为空，需要从数据库查询，再将数据存入redis
            //3.1 从数据库查询
            cs = categoryDao.findAll();
            //3.2 将集合数据存储到redis中的category的key
            for (int i = 0;i < cs.size();i++){
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }
        }else {
            logger.info("从redis中查询....");
            //4.如果不为空，将set的数据存入list
            cs = new ArrayList<Category>();
            for (Tuple tuple : categorys) {
                Category category = new Category();
                //category.setCname(name);
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                cs.add(category);
            }
        }

        return cs;
    }

}
