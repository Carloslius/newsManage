package edu.newelec.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.newelec.domain.News;
import edu.newelec.domain.NewsPro;
import edu.newelec.service.NewsProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/newsPro")
public class NewsProController {

    @Autowired
    NewsProService newsProService;

    @RequestMapping("/showNewsProList")
    public String showNewsProList(Model model, Long currentPage, Long size){
        IPage<NewsPro> newsProPage = newsProService.showNewsPro(currentPage, size);
        model.addAttribute("newsProList", newsProPage.getRecords());
        model.addAttribute("newsProPage", newsProPage.getPages());
        model.addAttribute("newsProTotal", newsProPage.getTotal());
        model.addAttribute("newsProCurrentPage", newsProPage.getCurrent());
        model.addAttribute("newsProSize", newsProPage.getSize());
        return "admin/ColumnManage";
    }

    @RequestMapping("/addNewsPro")
    public String addNewsPro(NewsPro newsPro, Long currentPage, Long size){
        newsProService.save(newsPro);
        return "redirect:/newsPro/showNewsProList?currentPage=" + currentPage + "&size=" + size;
    }

    @RequestMapping("/deleteNewsProByIdBatch")
    public String deleteNewsProByIdBatch(Integer[] id){
        if (id != null){
            List<Integer> list = new ArrayList<>();
            for (Integer num : id){
                list.add(num);
            }
            newsProService.removeByIds(list);
        }
        return "redirect:/newsPro/showNewsProList?currentPage=1&size=5";
    }

    @RequestMapping("/updateNewsProById")
    public String updateNewsProById(NewsPro newsPro){
        newsProService.updateById(newsPro);
        return "redirect:/newsPro/showNewsProList?currentPage=1&size=5";
    }

    @RequestMapping("/updateNewsProByIdInModel")
    public String updateNewsProByIdInModel(NewsPro newsPro){
        newsProService.updateByIdInModel(newsPro);
        return "redirect:/newsPro/showNewsProList?currentPage=1&size=5";
    }
}
