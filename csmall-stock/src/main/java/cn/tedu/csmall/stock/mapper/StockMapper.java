package cn.tedu.csmall.stock.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMapper {
    //修改(减少)指定商品库存的方法
    @Update("UPDATE stock_tbl SET COUNT=COUNT-#{reduceCount} WHERE commodity_code=#{commodityCode} AND COUNT>=#{reduceCount}")
    void updateStockByCommodityCode(@Param("commodityCode") String commodityCode,
                                    @Param("reduceCount") Integer reduceCount);
}
