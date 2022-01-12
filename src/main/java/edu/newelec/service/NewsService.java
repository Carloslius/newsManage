package edu.newelec.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.newelec.domain.News;

import java.util.List;

public interface NewsService extends IService<News> {

    Integer publishNewsById(Integer id);
    News listById(Integer id);
    List<News> listNewsPublished();
    List<News> showNewsByProId(Integer id);
    List<News> showNewsKeywordPublished(String keyword);
    IPage<News> showNews(Long currentPage, Long size);
    IPage<News> showNews(Long currentPage, Long size, String keyword);

}
