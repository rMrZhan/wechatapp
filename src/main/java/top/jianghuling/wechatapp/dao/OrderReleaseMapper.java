package top.jianghuling.wechatapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.jianghuling.wechatapp.entity.OrderRelease;
import top.jianghuling.wechatapp.entity.OrderReleaseExample;
@Mapper
@Repository
public interface OrderReleaseMapper {
    long countByExample(OrderReleaseExample example);

    int deleteByExample(OrderReleaseExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(OrderRelease record);

    int insertSelective(OrderRelease record);

    List<OrderRelease> selectByExample(OrderReleaseExample example);

    OrderRelease selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") OrderRelease record, @Param("example") OrderReleaseExample example);

    int updateByExample(@Param("record") OrderRelease record, @Param("example") OrderReleaseExample example);

    int updateByPrimaryKeySelective(OrderRelease record);

    int updateByPrimaryKey(OrderRelease record);

    List<OrderRelease> selectByLimit(@Param("startIndex") int startIndex,@Param("offset") int offset);
}