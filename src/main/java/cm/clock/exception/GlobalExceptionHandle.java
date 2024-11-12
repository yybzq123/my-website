package cm.clock.exception;


import cm.clock.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice//全局异常处理器
public class GlobalExceptionHandle {
    @ExceptionHandler(Exception.class)//处理所有异常
    public Result handleException(Exception e)
    {
        e.printStackTrace();// 控制台打印异常信息
        return Result.error(StringUtils.hasLength(e.getMessage())?e.getMessage():"服务器异常");
    }
}
