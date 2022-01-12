package edu.newelec.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.newelec.dao.NewsMapper;
import edu.newelec.domain.News;
import edu.newelec.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Autowired
    NewsMapper newsMapper;

    @Override
    public List<News> listNewsPublished() {
        LambdaQueryWrapper<News> lqw = new LambdaQueryWrapper<>();
        lqw.eq(News::getState, 1);
        return newsMapper.selectList(lqw);
    }

    @Override
    public List<News> showNewsKeywordPublished(String keyword) {
        LambdaQueryWrapper<News> lqw = new LambdaQueryWrapper<>();
        lqw.eq(News::getState, 1).like(keyword != null, News::getTitle, keyword);
        return newsMapper.selectList(lqw);
    }

    @Override
    public Integer publishNewsById(Integer id) {
        News news = new News();
        news.setState(1);
        LambdaQueryWrapper<News> lqw = new LambdaQueryWrapper<>();
        lqw.eq(id != 0, News::getId, id);
        return newsMapper.update(news, lqw);
    }

    @Override
    public News listById(Integer id) {
        return newsMapper.selectById(id);
    }

    @Override
    public List<News> showNewsByProId(Integer id) {
        LambdaQueryWrapper<News> lqw = new LambdaQueryWrapper<>();
        lqw.eq(id >= 0, News::getProCode, id).eq(News::getState, 1);
        return newsMapper.selectList(lqw);
    }

    /**
     * 分页查询（显示）News
     * @param currentPage 当前页码
     * @param size 一页上显示的数据数
     * @return 返回指定页码，指定数量的News
     */
    @Override
    public IPage<News> showNews(Long currentPage, Long size) {
        IPage<News> newsPage = new Page<>(currentPage, size);
        return newsMapper.selectPage(newsPage, null);
    }

    @Override
    public IPage<News> showNews(Long currentPage, Long size, String keyword) {
        IPage<News> newsPage = new Page<>(currentPage, size);
        LambdaQueryWrapper<News> lqw = new LambdaQueryWrapper<>();
        lqw.like(keyword != null, News::getTitle, keyword);
        return newsMapper.selectPage(newsPage, lqw);
    }

}
