package cm.clock.controller;

import cm.clock.pojo.Result;
import cm.clock.pojo.User;
import cm.clock.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    @GetMapping("/rank/list")
    public Result<List<User>> list(){
        List<User> list =apiService.list();
        Collections.sort(list);
        return Result.success("排行成功",list);

    }

}
