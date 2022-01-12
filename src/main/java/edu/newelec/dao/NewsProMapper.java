package edu.newelec.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.newelec.domain.NewsPro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface NewsProMapper extends BaseMapper<NewsPro> {

    @Update("update news_pro set name = #{name}, sort = #{sort} where id = #{id}")
    Integer updateNameAndSortById(Integer id, String name, Integer sort);
    @Update("update news_pro set name = #{name} where id = #{id}")
    Integer updateNameByIdInModel(Integer id, String name);
    @Update("update news_pro set sort = #{sort} where id = #{id}")
    Integer updateSortByIdInModel(Integer id, Integer sort);
}
