package cm.clock.service;

import cm.clock.pojo.DTO.forgetPwdDTO;
import cm.clock.pojo.User;

public interface UserService {
    void appRegister(String studentId, String password, String name );

    User findByUserNameId(String studentId);

    void forgetPwd(forgetPwdDTO forgetPwdDTO);

    User getUserInfo(String studentId);
}
