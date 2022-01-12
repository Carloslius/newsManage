package edu.newelec.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.newelec.domain.News;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsMapper extends BaseMapper<News> {
}
