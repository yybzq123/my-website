package cm.clock.controller;

import cm.clock.pojo.Result;
import cm.clock.pojo.User;
import cm.clock.service.ApiService;
import cm.clock.service.UserService;
import cm.clock.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {
    @Autowired
    private ApiService apiService;

    @Autowired
    private UserService userService;

    /**
     * 排行榜
     * @return
     */
    @GetMapping("/rank/list")
    public Result<List<User>> list(){
        List<User> list =apiService.list();
        return Result.success("排行成功",list);

    }

    /**
     * 打卡记时
     * @return
     */
    //TODO
@PostMapping("/applicationxiugai")
    public Result AppClockIn(){
    //获取用户id
    Map<String,Object> map = ThreadLocalUtil.get();
    String studentId = (String) map.get(("studentId"));
    User userInfo = userService.getUserInfo(studentId);
    if (userInfo.getStatus()==0){//未打卡
        log.info("用户{}进入打卡",studentId);
        apiService.clockIn(studentId);
    }else {
        log.info("用户{}离开打卡" , studentId );
        apiService.clockOut(studentId);
    }
    return Result.success("成功");


//    User user=userService.getUserInfo(studentId);
//    Integer status= user.getStatus();
//    if(status==0){//未打卡
//        //将状态调整为1，代表正在打卡
//        //将用户信息存放到clockIn中,设置时间为系统的当前时间
//
//    }else {//正在打卡
//        //将状态调整为0，代表未打卡
//        //计算打卡时长：当前系统时间-用户打卡点的时间
//        //将新增加的打卡时长加上原来的打卡时长存入student中
//        //删除clockIn中的用户信息
//    }
//    return Result.success("打卡成功");
}

    @GetMapping("/info")
    public Result<User> info(){
        Map<String,Object> map = ThreadLocalUtil.get();
        String studentId = (String) map.get(("studentId"));
        log.info("获取用户信息"+ studentId);

        User userInfo = userService.getUserInfo(studentId);
        return Result.success("获取成功",userInfo);
    }



}
