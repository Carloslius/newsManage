package edu.newelec.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.newelec.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void showUsers(){
        IPage<User> userPage = userService.showUsers((long)2, (long)3);
    }
}
