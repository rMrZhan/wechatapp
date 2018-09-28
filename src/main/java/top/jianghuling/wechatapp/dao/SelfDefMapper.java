package top.jianghuling.wechatapp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.jianghuling.wechatapp.model.BriefOrder;
import top.jianghuling.wechatapp.model.Mission;
import top.jianghuling.wechatapp.model.Order;
import top.jianghuling.wechatapp.model.OrderLinkMission;

import java.util.List;


@Mapper
@Repository
public interface SelfDefMapper {

    //created by Jason @2018/9/9
    List<BriefOrder> selectBriefOrderByPage(@Param("userId")String userId, @Param("startIndex")int startIndex, @Param("pageSize")int pageSize, @Param("orderState")int orderState);

    //created by Jason @2018/9/11
    List<OrderLinkMission> selectMyRelease(@Param("hostId")String hostId, @Param("startIndex")int startIndex, @Param("pageSize")int pageSize );

    //created by Jason @2018/9/11
    int updateByKeyLimitVsn(Order record);

    //created by Jason @2018/9/11
    int updateStateLock(@Param("orderId") String orderId, @Param("newState") Byte newState,@Param("version")int version);

    //created by Jason @2018/9/28
    List<OrderLinkMission> selectMyAccept(@Param("takerId")String takerId, @Param("startIndex")int startIndex, @Param("pageSize")int pageSize);

    //create by Jason @2018/9/16
    Mission selectByOrderId(@Param("orderId") String orderId, @Param("orderState")int orderState);


}
