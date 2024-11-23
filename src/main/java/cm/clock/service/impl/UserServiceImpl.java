package cm.clock.service.impl;

import cm.clock.mapper.UserMapper;
import cm.clock.pojo.DTO.updatePwdDTO;
import cm.clock.pojo.User;
import cm.clock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



    @Override
    public void forgetPwd(updatePwdDTO forgetPwdDTO) {
        usermapper.updatePwd(forgetPwdDTO);
    }

    @Override
    public User getUserInfo(String studentId) {
        //先获取用户打卡状态
        int status = usermapper.countByStudentId(studentId);
        //把状态添加到用户信息中
        User userInfo = usermapper.getUserInfo(studentId);
        userInfo.setStatus(status);
        return userInfo;
    }

}
