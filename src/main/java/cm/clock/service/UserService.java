package cm.clock.service;

import cm.clock.pojo.User;

import java.util.List;

public interface UserService {
    void appRegister(String studentId, String password, String name );

    User findByUserNameId(String studentId);


}
