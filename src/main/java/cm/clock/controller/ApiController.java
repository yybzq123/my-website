package cm.clock.controller;

import cm.clock.pojo.Result;
import cm.clock.pojo.User;
import cm.clock.service.ApiService;
import cm.clock.service.UserService;
import cm.clock.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
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
        Collections.sort(list);
        return Result.success("排行成功",list);

    }

    /**
     * 打卡记时
     * @return
     */
    //TODO
@GetMapping("/applicationxiugai")
    public Result AppClockIn(){
    //获取用户id
    Map<String,Object> map = ThreadLocalUtil.get();
    String studentId = (String) map.get(("student_id"));
    //获取用户状态
    User user=userService.getUserInfo(studentId);
    Integer status= user.getStatus();
    if(status==0){//未打卡
        //将状态调整为1，代表正在打卡
        //将用户信息存放到clockIn中,设置时间为系统的当前时间

    }else {//正在打卡
        //将状态调整为0，代表未打卡
        //计算打卡时长：当前系统时间-用户打卡点的时间
        //将新增加的打卡时长加上原来的打卡时长存入student中
        //删除clockIn中的用户信息
    }
    return Result.success("打卡成功");
}



}
