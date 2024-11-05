package com.zjtec.travel.dao;

import com.zjtec.travel.domain.Route;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteDao {

    /**
     * 根据cid查询总记录数
     * @param cid
     * @return
     */
    int findTotalCount(int cid);

    /**
     * 根据cid，start，pageSize查询当前页的数据集合
     * @param cid
     * @param start
     * @param pageSize
     * @return
     */
    List<Route> findByPage(@Param("cid") int cid, @Param("start")int start, @Param("pageSize")int pageSize);

    /**
     * 根据rid查询
     * @param rid
     * @return
     */
    Route findByRid(@Param("rid") int rid);
}
