package cm.clock.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//添加getter/setter/toString方法,Spring将Result对象转化为JSON数据，调用toString方法
@NoArgsConstructor//提添加无参数的构造方法
@AllArgsConstructor//添加有参数的构造方法
public class Result<T> {
    private Integer code;//业务状态码  200成功 402失败
    private String message;//提示信息
    private T data;//响应数据

    //快速返回操作成功响应结果(带响应数据)
    public static <E> Result<E> success(String message,E data) {
        return new Result<>(200, message, data);
    }

    //快速返回操作成功响应结果
    public static Result success(String message) {
        return new Result(200, message, null);
    }

    public static Result error(String message) {
        return new Result(402, message, null);
    }
}
