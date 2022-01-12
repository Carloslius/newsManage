package edu.newelec.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.newelec.domain.User;

public interface UserService extends IService<User> {

    String getAuthorById(Integer id);
    User loginByAcctAndPwd(User user);
    IPage<User> showUsers(Long currentPage, Long size);
    IPage<User> showUsers(Long currentPage, Long size, String keyword);
    Integer updateUserById(User user);
    Integer grantUserPermissionByIdBatch(Integer[] id, Integer permission);
}
