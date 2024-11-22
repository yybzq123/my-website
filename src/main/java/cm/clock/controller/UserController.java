package cm.clock.controller;

import cm.clock.pojo.DTO.updatePwdDTO;
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
            claims.put("StudentId",user.getStudentId());
            claims.put("name",user.getName());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);

        }
        else {
            return Result.error("密码或者id错误");
        }
    }

     /**
     * 更新密码
     */
@PostMapping("/updatePwd")
    public Result updatePwd(@RequestBody updatePwdDTO updatePwdDTO){
        Map<String,Object> map= ThreadLocalUtil.get();
        //验证用户信息
        if(!updatePwdDTO.getStudentId().equals(map.get("StudentId"))&&updatePwdDTO.getName().equals(map.get("name")))
            return Result.error("信息错误");
        //验证两次密码是否一致
        if(!Objects.equals(updatePwdDTO.getNewPwd(), updatePwdDTO.getReNewPwd()))
            return Result.error("两次密码不一致");
        updatePwdDTO.setNewPwd(Md5Util.getMD5String(updatePwdDTO.getNewPwd()));
        userservice.updatePwd(updatePwdDTO);
        return Result.success("更新密码成功");
    }




}
