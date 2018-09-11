package top.jianghuling.wechatapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.jianghuling.wechatapp.entity.Order;
import top.jianghuling.wechatapp.entity.OrderExample;
import top.jianghuling.wechatapp.entity.OrderLinkMission;
import top.jianghuling.wechatapp.results.BriefOrder;

@Repository
@Mapper
public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    //created by Jason @2018/9/9
    List<BriefOrder> selectBriefOrderByPage(@Param("startIndex")int startIndex, @Param("pageSize")int pageSize, @Param("orderState")int orderState);
    //created by Jason @2018/9/11
    List<OrderLinkMission> selectOrderMission(@Param("hostId")String hostId,@Param("startIndex")int startIndex,@Param("pageSize")int pageSize );
}