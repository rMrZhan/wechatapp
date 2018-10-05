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

    /**
     * @author Jason
     */
    List<BriefOrder> selectBriefOrder(@Param("userId")String userId, @Param("startIndex")int startIndex, @Param("pageSize")int pageSize, @Param("orderState")int orderState);

    /**
     * @author Jason
     */
    List<OrderLinkMission> selectMyRelease(@Param("hostId")String hostId, @Param("startIndex")int startIndex, @Param("pageSize")int pageSize );

    /**
     * @author Jason
     */
    List<BriefOrder> selectAllBriefOrder(@Param("userId")String userId, @Param("startIndex")int startIndex, @Param("pageSize")int pageSize);

    /**
     * @author Jason
     */
    int updateStateLock(@Param("orderId") String orderId, @Param("newState") Byte newState,@Param("version")int version);

    /**
     * @author Jason
     */
    List<OrderLinkMission> selectMyAccept(@Param("takerId")String takerId, @Param("startIndex")int startIndex, @Param("pageSize")int pageSize);

    /**
     * @author Jason
     */
    Mission selectMissionByOrderId(@Param("orderId") String orderId, @Param("orderState")int orderState);


}
