package cm.clock.utils;

import ch.qos.logback.core.util.MD5Util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;


@Component
public class JwtUtil {

    private static final String KEY = "itheima";
	
	//接收业务数据,生成token并返回
    public static String genToken(Map<String, Object> claims) throws NoSuchAlgorithmException {
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                .sign(Algorithm.HMAC256(KEY));
    }

	//接收token,验证token,并返回业务数据
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }

}
