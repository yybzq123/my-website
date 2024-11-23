package cm.clock.service.impl;

import cm.clock.mapper.ApiMapper;
import cm.clock.mapper.UserMapper;
import cm.clock.pojo.User;
import cm.clock.service.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.List;

import static java.time.LocalDateTime.*;

@Service
@Slf4j
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiMapper apiMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list() {
        return apiMapper.list();
    }

    @Override
    public void clockIn(String studentId) {
        apiMapper.clockIn(studentId);
    }

    @Override
    public void clockOut(String studentId) {
        // 获取学生的开始时间
        LocalDateTime startTime = apiMapper.getStartTime(studentId);
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 计算时间差
        Duration duration = Duration.between(startTime, now);
        log.info("{}本次打卡时间差:{}分钟", studentId ,duration.toMinutes());
        User userInfo = userMapper.getUserInfo(studentId);
        userInfo.setTime(userInfo.getTime() + duration.toMinutes());
        userMapper.updateTime(userInfo.getTime() , studentId);
        userMapper.deleteClock(studentId);
    }


}
