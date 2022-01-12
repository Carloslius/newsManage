package edu.newelec.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.newelec.domain.News;
import edu.newelec.domain.NewsEcho;
import edu.newelec.domain.NewsPro;
import edu.newelec.service.NewsProService;
import edu.newelec.service.NewsService;
import edu.newelec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    UserService userService;
    @Autowired
    NewsService newsService;
    @Autowired
    NewsProService newsProService;

    // 应该写在newsProController
    @RequestMapping("postNews.html")
    public String postNewsHtml(Model model){
        List<NewsPro> newsProList = newsProService.list();
        model.addAttribute("newsProList", newsProList);
        return "admin/PostNews";
    }

    @RequestMapping("/showNewsList")
    public ModelAndView showNewsList(Long currentPage, Long size, String keyword){
        ModelAndView modelAndView = new ModelAndView();
        List<NewsEcho> newsList = new ArrayList<>();
        IPage<News> newsPage = newsService.showNews(currentPage, size, keyword);
        for (News news : newsPage.getRecords()){
            NewsEcho newsEcho = new NewsEcho();
            newsEcho.setId(news.getId());
            newsEcho.setTitle(news.getTitle());
            newsEcho.setDeScr(news.getDeScr());
            newsEcho.setMainImg(news.getMainImg());
            newsEcho.setDetail(news.getDetail());
            newsEcho.setSource(news.getSource());
            newsEcho.setView(news.getView());
            newsEcho.setTop(news.getTop());
            newsEcho.setState(news.getState());
            newsEcho.setCrTime(news.getCrTime());
            newsEcho.setUpTime(news.getUpTime());
            newsEcho.setProCode(newsProService.getNameByProId(news.getProCode()));
            newsEcho.setAuthor(userService.getAuthorById(news.getAuthor()));
            newsList.add(newsEcho);
        }
        modelAndView.addObject("newsList", newsList);
        modelAndView.addObject("newsPage", newsPage.getPages());
        modelAndView.addObject("newsTotal", newsPage.getTotal());
        modelAndView.addObject("newsCurrentPage", newsPage.getCurrent());
        modelAndView.addObject("newsSize", newsPage.getSize());
        modelAndView.setViewName("admin/NewsManager");
        return modelAndView;
    }

    @RequestMapping("/deleteNewsById")
    public String deleteNewsById(Integer id, Long currentPage, Long size){
        newsService.removeById(id);
        return "redirect:/news/showNewsList?currentPage=" + currentPage + "&size=" + size;
    }

    @RequestMapping("/publishNewsById")
    public String publishNewsById(Integer id, Long currentPage, Long size){
        newsService.publishNewsById(id);
        return "redirect:/news/showNewsList?currentPage=" + currentPage + "&size=" + size;
    }

    @PostMapping("/saveNews")
    public String addNews(News news, Long currentPage, Long size){
        newsService.save(news);
        return "redirect:/news/showNewsList?currentPage=" + currentPage + "&size=" + size;
    }
}
