package edu.newelec.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.newelec.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where acct = '${acct}' and pwd = '${pwd}'")
    User queryUserByByAcctAndPwd(User user);

}
