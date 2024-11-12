package cm.clock.controller;

import cm.clock.pojo.Result;
import cm.clock.pojo.User;
import cm.clock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class UserController {

    @Autowired
    private UserService userservice;
/**
 * 注册
 */
    @PostMapping("/appregister")
    public Result appregister(String studentId,String password,String name)
    {
        User user = userservice.findByUserName(studentId);
        if(user!=null)
        {
            userservice.appRegister( studentId, password, name);
            return Result.success("注册成功");
        }
        else {
            return Result.error("用户已存在");
        }
    }


}
