package edu.newelec.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.newelec.domain.News;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class NewsServiceTest {

    @Autowired
    NewsService newsService;

    @Test
    public void updateNewsStateById(){
        News news = new News();
        news.setId(4);
        news.setState(1);
        newsService.updateById(news);
    }

    @Test
    public void updateNewsStateByTitle(){
        News news = new News();
        news.setTitle("测试1");
        news.setTop(1);
        LambdaQueryWrapper<News> lqw = new LambdaQueryWrapper<>();
        lqw.eq(news.getTitle() != null, News::getTitle, "测试");
        newsService.update(news, lqw);
    }

    @Test
    public void insertNews(){
        News news = new News();
        news.setTitle("测试1");
        news.setDeScr("测试1");
        news.setProCode(0);
        news.setMainImg("测试");
        news.setDetail("测试");
        news.setSource("测试");
        news.setView(0);
        news.setTop(0);
        news.setState(0);
        news.setAuthor(1);
        news.setCrTime(new Date());
        news.setUpTime(new Date());
        newsService.save(news);
    }

    @Test
    public void deleteNewsByTitle(){
        News news = new News();
        news.setTitle("1");
        LambdaQueryWrapper<News> lqw = new LambdaQueryWrapper<>();
        lqw.like(news.getTitle() != null, News::getTitle, news.getTitle());
        newsService.remove(lqw);
    }
}
