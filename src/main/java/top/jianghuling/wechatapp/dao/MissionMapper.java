package top.jianghuling.wechatapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.jianghuling.wechatapp.entity.Mission;
import top.jianghuling.wechatapp.entity.MissionExample;

@Repository
@Mapper
public interface MissionMapper {
    long countByExample(MissionExample example);

    int deleteByExample(MissionExample example);

    int deleteByPrimaryKey(String missionId);

    int insert(Mission record);

    int insertSelective(Mission record);

    List<Mission> selectByExample(MissionExample example);

    Mission selectByPrimaryKey(String missionId);

    int updateByExampleSelective(@Param("record") Mission record, @Param("example") MissionExample example);

    int updateByExample(@Param("record") Mission record, @Param("example") MissionExample example);

    int updateByPrimaryKeySelective(Mission record);

    int updateByPrimaryKey(Mission record);
}