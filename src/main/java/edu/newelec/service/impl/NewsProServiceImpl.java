package edu.newelec.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.newelec.dao.NewsProMapper;
import edu.newelec.domain.NewsPro;
import edu.newelec.service.NewsProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsProServiceImpl extends ServiceImpl<NewsProMapper, NewsPro> implements NewsProService {

    @Autowired
    NewsProMapper newsProMapper;

    @Override
    public Integer updateByIdInModel(NewsPro newsPro) {
        String name = newsPro.getName();
        Integer sort = newsPro.getSort();
        if (name != "" && sort != null) {
            return newsProMapper.updateNameAndSortById(newsPro.getId(), name, sort);
        }else if (name != ""){
            return newsProMapper.updateNameByIdInModel(newsPro.getId(), name);
        }else if (sort != null){
            return newsProMapper.updateSortByIdInModel(newsPro.getId(), sort);
        }else {
            return -1;
        }
    }

    @Override
    public String getNameByProId(Integer id) {
        return newsProMapper.selectById(id).getName();
    }

    /**
     * 分页查询（显示）NewsPro
     * @param currentPage 当前页码
     * @param size 一页上显示的数据数
     * @return 返回指定页码，指定数量的NewsPro
     */
    @Override
    public IPage<NewsPro> showNewsPro(Long currentPage, Long size) {
        IPage<NewsPro> newsProPage = new Page<>(currentPage, size);
        return newsProMapper.selectPage(newsProPage, null);
    }
}
