package cn.coderap.server.mapper;


import cn.coderap.server.pojo.Hello;
import cn.coderap.server.pojo.HelloExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 通过Example可以帮助我们写入where、order by、distinct等，
 * 熟练掌握可以极大提高单表的开发效率
 */
public interface HelloMapper {
    long countByExample(HelloExample example);

    int deleteByExample(HelloExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Hello record);

    int insertSelective(Hello record);

    List<Hello> selectByExample(HelloExample example);

    Hello selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Hello record, @Param("example") HelloExample example);

    int updateByExample(@Param("record") Hello record, @Param("example") HelloExample example);

    int updateByPrimaryKeySelective(Hello record);

    int updateByPrimaryKey(Hello record);
}