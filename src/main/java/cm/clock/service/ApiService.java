package cm.clock.service;

import cm.clock.pojo.User;

import java.util.List;


public interface ApiService {
    List<User> list();


    void clockIn(String studentId);

    void clockOut(String studentId);
}
