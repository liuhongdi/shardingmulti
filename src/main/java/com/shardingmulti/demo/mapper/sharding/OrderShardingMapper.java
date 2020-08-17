package com.shardingmulti.demo.mapper.sharding;

import com.shardingmulti.demo.pojo.OrderSharding;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderShardingMapper {
    //int insertOneOrder(Order orderOne);
    //String selectNameById(String userId);
    List<OrderSharding> selectAllOrder();
}