package cm.clock.service.impl;

import cm.clock.mapper.ApiMapper;
import cm.clock.pojo.User;
import cm.clock.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiMapper apiMapper;
    @Override
    public List<User> list() {
        return apiMapper.list();
    }




}
