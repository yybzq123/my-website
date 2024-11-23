package cm.clock.service;

import cm.clock.pojo.DTO.updatePwdDTO;
import cm.clock.pojo.User;

public interface UserService {
    void appRegister(String studentId, String password, String name );

    User findByUserNameId(String studentId);

    void forgetPwd( updatePwdDTO forgetPwdDTO);

    User getUserInfo(String studentId);
}
