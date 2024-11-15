package cm.clock.service.impl;

import cm.clock.mapper.UserMapper;
import cm.clock.pojo.User;
import cm.clock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper usermapper;

    /**
     * 注册
     */
    @Override
    public void appRegister(String studentId, String password, String name ) {
        usermapper.appregister( studentId, password, name );
    }

    /**
     * 根据用户名查询用户信息
     */
    @Override
    public User findByUserNameId(String studentId) {

        return usermapper.findByUserName(studentId);
    }


}
