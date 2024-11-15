package cm.clock.controller;

import cm.clock.pojo.Result;
import cm.clock.pojo.User;
import cm.clock.service.UserService;
import cm.clock.utils.JwtUtil;
import cm.clock.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/app")

public class UserController {

    @Autowired
    private UserService userservice;
/**
 * 注册
 */
    @PostMapping("/appregister")
    public Result appregister(@RequestBody @Validated(User.Register.class) User user)
    {
          User newuser = userservice.findByUserNameId(user.getStudentId());
        if(newuser==null)
        {
            userservice.appRegister( user.getStudentId(), Md5Util.getMD5String(user.getPassword()), user.getName());
            return Result.success("注册成功");
        }
        else {
            return Result.error("用户已存在");
        }
    }

    /**
     * 登录
     */
    @PostMapping("/applogin")
    public Result login(@RequestBody @Validated(User.Login.class) User user) throws NoSuchAlgorithmException {
        User newuser =userservice.findByUserNameId(user.getStudentId());
        if(newuser!=null&&Md5Util.checkPassword(user.getPassword(),newuser.getPassword()))
        {
            Map<String, Object> claims = new HashMap<>();
            claims.put("StudentId",user.getStudentId());
            claims.put("name",user.getName());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);

        }
        else {
            return Result.error("密码或者id错误");
        }
    }






}
