package cm.clock.service;

import cm.clock.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ApiService {
    List<User> list();
}
