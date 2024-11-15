package cm.clock.config;


import cm.clock.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor logininterceptor;

    /**
     * 添加拦截器配置
     *
     * 该方法用于向应用程序添加拦截器，以在请求处理之前或之后执行特定逻辑
     * 特别地，本方法添加了一个登录拦截器，以排除未登录用户访问特定资源
     *
     * @param registry InterceptorRegistry的实例，用于注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册登录拦截器，并排除登录和注册路径，允许公共访问
        registry.addInterceptor(logininterceptor).excludePathPatterns("/app/applogin","/app/appregister");
    }


}
