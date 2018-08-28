package top.jianghuling.wechatapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.jianghuling.wechatapp.entity.OrderAccept;
import top.jianghuling.wechatapp.entity.OrderAcceptExample;

@Mapper
@Repository
public interface OrderAcceptMapper {
    long countByExample(OrderAcceptExample example);

    int deleteByExample(OrderAcceptExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(OrderAccept record);

    int insertSelective(OrderAccept record);

    List<OrderAccept> selectByExample(OrderAcceptExample example);

    OrderAccept selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") OrderAccept record, @Param("example") OrderAcceptExample example);

    int updateByExample(@Param("record") OrderAccept record, @Param("example") OrderAcceptExample example);

    int updateByPrimaryKeySelective(OrderAccept record);

    int updateByPrimaryKey(OrderAccept record);

    //限制取出的数据数量
    List<OrderAccept> selectByTakerPhoneLimit(@Param("phone")String phone ,@Param("startIndex") int startIndex, @Param("offset")int offset);
        }