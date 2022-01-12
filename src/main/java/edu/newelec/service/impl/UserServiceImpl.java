package edu.newelec.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.newelec.dao.UserMapper;
import edu.newelec.domain.News;
import edu.newelec.domain.User;
import edu.newelec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public String getAuthorById(Integer id) {
        return userMapper.selectById(id).getName();
    }

    @Override
    public User loginByAcctAndPwd(User user) {
        return userMapper.queryUserByByAcctAndPwd(user);
    }

    /**
     * 分页查询（显示）User
     * @param currentPage 当前页码
     * @param size 一页上显示的数据数
     * @return 返回指定页码，指定数量的User
     */
    @Override
    public IPage<User> showUsers(Long currentPage, Long size) {
        IPage<User> userPage = new Page<>(currentPage, size);
        return userMapper.selectPage(userPage, null);
    }

    @Override
    public IPage<User> showUsers(Long currentPage, Long size, String keyword) {
        IPage<User> userPage = new Page<>(currentPage, size);
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.like(keyword != null, User::getName, keyword);
        return userMapper.selectPage(userPage, lqw);
    }

    @Override
    public Integer updateUserById(User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(user.getId() > 0, User::getId, user.getId());
        return userMapper.update(user, lqw);
    }

    @Override
    public Integer grantUserPermissionByIdBatch(Integer[] id, Integer permission) {
        if (id != null) {
            for (Integer num : id) {
                User user = new User();
                user.setId(num);
                user.setPermission(permission);
                userMapper.updateById(user);
            }
        }
        return 0;
    }
}
