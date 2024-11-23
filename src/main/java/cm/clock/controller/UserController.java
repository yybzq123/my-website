package cm.clock.controller;

import cm.clock.pojo.DTO.forgetPwdDTO;
import cm.clock.pojo.Result;
import cm.clock.pojo.User;
import cm.clock.service.UserService;
import cm.clock.utils.JwtUtil;
import cm.clock.utils.Md5Util;
import cm.clock.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
            claims.put("studentId",user.getStudentId());
            claims.put("name",user.getName());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);

        }
        else {
            return Result.error("密码或者id错误");
        }
    }

     /**
     * 忘记密码
     */
@PostMapping("/updatePwd")
    public Result forgetPwd(@RequestBody forgetPwdDTO forgetPwdDTO){

        //验证用户是否存在
        String studentId =forgetPwdDTO.getStudentId();
        User user=userservice.findByUserNameId(studentId);
        if(user==null)
            return Result.error("用户不存在");
        String name=user.getName();
        if(!name.equals(user.getName()))
            return Result.error("用户信息错误");
        //验证两次密码是否一致
        if(!Objects.equals(forgetPwdDTO.getNewPwd(), forgetPwdDTO.getReNewPwd()))
            return Result.error("两次密码不一致");
        forgetPwdDTO.setNewPwd(Md5Util.getMD5String(forgetPwdDTO.getNewPwd()));
        userservice.forgetPwd(forgetPwdDTO);
        return Result.success("更新密码成功");
    }

/**
 * 获取用户信息
 */
    @GetMapping("/getUserInfo")
    public Result<User> getUserInfo(){
        Map<String,Object> map=ThreadLocalUtil.get();
        String studentId = (String) map.get("studentId");
        User user=userservice.getUserInfo(studentId);
        return Result.success("获取成功",user);

    }



}
