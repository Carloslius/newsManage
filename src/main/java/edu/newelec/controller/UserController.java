package edu.newelec.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.newelec.domain.News;
import edu.newelec.domain.User;
import edu.newelec.service.NewsProService;
import edu.newelec.service.NewsService;
import edu.newelec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    NewsService newsService;
    @Autowired
    NewsProService newsProService;

    @RequestMapping("/userSetting.html")
    public String userSetting(){
        return "admin/UserSetting";
    }

    @RequestMapping("/login")
    public String login(User user, HttpSession session){
        User loginUser = userService.loginByAcctAndPwd(user);
        if (loginUser == null){
            return "redirect:defeat.html";
        }else {
            session.setAttribute("loginUser", loginUser);
            return "redirect:success.html";
        }
    }
    @RequestMapping("/success.html")
    public String mainPage(Model model){
        model.addAttribute("newsProCount", newsProService.count());
        Integer newsCount = newsService.count();
        model.addAttribute("newsCount", newsCount);
        List<News> list = newsService.list();
        List<News> newsList = new ArrayList<>();
        /*for (int index = newsCount-1, count = 0; count < 3; index--, count++){
            newsList.add(list.get(index));
        }*/
        for (int index = 6; index < 8; index++) {
            newsList.add(list.get(index));
        }
        model.addAttribute("newsList", newsList);
        return "admin/loginSuccess";
    }
    @RequestMapping("/defeat.html")
    public String defeatPage(){
        return "admin/loginDefeat";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping("/showUserList")
    public ModelAndView showUserList(Long currentPage, Long size){
        ModelAndView modelAndView = new ModelAndView();
        IPage<User> userPage = userService.showUsers(currentPage, size);
        modelAndView.addObject("userList", userPage.getRecords());
        modelAndView.addObject("userPage", userPage.getPages());
        modelAndView.addObject("userTotal", userPage.getTotal());
        modelAndView.addObject("userCurrentPage", userPage.getCurrent());
        modelAndView.addObject("userSize", userPage.getSize());
        modelAndView.setViewName("admin/UserManage");
        return modelAndView;
    }

    @RequestMapping("/searchUserKeyWord")
    public String searchUserKeyWord(Model model, String keyword){
        IPage<User> userPage = userService.showUsers((long)1, (long)4, keyword);
        model.addAttribute("userList", userPage.getRecords());
        model.addAttribute("userPage", userPage.getPages());
        model.addAttribute("userTotal", userPage.getTotal());
        model.addAttribute("userCurrentPage", userPage.getCurrent());
        model.addAttribute("userSize", userPage.getSize());
        return "admin/UserManage";
    }

    @RequestMapping("/saveUser")
    public String saveUser(User user, Long currentPage, Long size){
        userService.save(user);
        return "redirect:/user/showUserList?currentPage=" + currentPage + "&size=" + size;
    }

    @RequestMapping("/updateUserById")
    public String updateUserById(User user, Long currentPage, Long size, HttpSession session){
        userService.updateUserById(user);
        User loginUser = (User)session.getAttribute("loginUser");
        loginUser.setName(user.getName());
        session.setAttribute("loginUser", loginUser);
        return "redirect:/user/showUserList?currentPage=" + currentPage + "&size=" + size;
    }

    @RequestMapping("/deleteUserByIdBatch")
    public String deleteUserByIdBatch(Integer[] id){
        if (id != null) {
            List<Integer> list = new ArrayList<>();
            for (Integer num : id){
                list.add(num);
            }
            userService.removeByIds(list);
        }
        return "redirect:/user/showUserList?currentPage=1&size=4";
    }

    @RequestMapping("/grantUserPermissionByIdBatch")
    public String grantUserPermissionByIdBatch(Integer[] ids, Integer permission){
        userService.grantUserPermissionByIdBatch(ids, permission);
        return "redirect:/user/showUserList?currentPage=1&size=4";
    }
}
