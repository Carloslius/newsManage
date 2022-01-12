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
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class InitController {

    @Autowired
    UserService userService;

    @Autowired
    NewsService newsService;

    @Autowired
    NewsProService newsProService;

    @RequestMapping("/")
    public String init(Model model){
        List<NewsPro> newsProList = newsProService.list();
        List<News> newsList = newsService.listNewsPublished();
        model.addAttribute("newsProList", newsProList);
        model.addAttribute("newsList", newsList);
        return "user/index";
    }

    @RequestMapping("/login")
    public String initLogin(HttpSession session){
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser != null){
            return "redirect:/user/success.html";
            //return "admin/loginSuccess";
        }
        return "admin/index";
    }

    @RequestMapping("/showNewsWithOutAt")
    public String showNewsWithOutAt(Model model, String keyword){
        List<News> newsList = newsService.showNewsKeywordPublished(keyword);
        model.addAttribute("newsList", newsList);
        List<NewsPro> newsProList = newsProService.list();
        model.addAttribute("newsProList", newsProList);
        model.addAttribute("msg", "查询结果");
        return "user/index";
    }

    @RequestMapping("/showNewsByProWithOutAt")
    public String showNewsByProWithOutAt(Model model, Integer proId){
        NewsPro newsPro = newsProService.getById(proId);
        List<News> newsList = newsService.showNewsByProId(newsPro.getId());
        model.addAttribute("newsList", newsList);
        List<NewsPro> newsProList = newsProService.list();
        model.addAttribute("newsProList", newsProList);
        model.addAttribute("msg", newsProService.getNameByProId(proId));
        return "user/index";
    }

    @RequestMapping("/showNewsDetail")
    public String showNewsDetail(Model model, Integer id){
        News news = newsService.listById(id);
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
        model.addAttribute("news", newsEcho);
        List<NewsPro> newsProList = newsProService.list();
        model.addAttribute("newsProList", newsProList);
        return "user/article";
    }
}
