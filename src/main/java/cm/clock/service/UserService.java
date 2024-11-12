package cm.clock.service;

import cm.clock.pojo.User;

public interface UserService {
    void appRegister(String studentId, String password, String name );

    User findByUserName(String name);
}
