package com.zzj.security.jwt;

import com.zzj.common.Hela;
import com.zzj.common.Odin;
import com.zzj.entity.User;
import com.zzj.service.UserService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Component
@Slf4j
public class JWTUtil {

    private static int timeout;
    private static String uniqueKEY;
    private static int refreshsinterval;

    @Value("${token.timeout}")
    public  void setTimeout(int timeout) {
        JWTUtil.timeout = timeout;
    }
    @Value("${token.uniqueKEY}")
    public  void setUniqueKEY(String uniqueKEY) {
        JWTUtil.uniqueKEY = uniqueKEY;
    }

    @Value("${token.refreshsinterval}")
    public  void setRefreshsinterval(int refreshsinterval) {
        JWTUtil.refreshsinterval = refreshsinterval;
    }

    private static JWTUtil ourInstance = new JWTUtil();

    public static JWTUtil getInstance() {
        return ourInstance;
    }

    private JWTUtil() {
    }

//    @Resource(name = "com.zzj.service.UserService")
//    private UserService userService;

//    public UserService getUserService() {
//        return userService;
//    }
//
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    /**
     * 获取初始化密钥 并解码
     * @return
     */
    public  SecretKey generalKey() {
        String stringKey = uniqueKEY;
        // 本地的密码解码
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        // 根据给定的字节数组使用AES加密算法构造一个密钥
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
    public  SecretKey ungeneralKey() {
        String stringKey = uniqueKEY;
        // 本地的密码解码
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        // 根据给定的字节数组使用AES加密算法构造一个密钥
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }


    /**
     * 刷新token
     * @param user
     * @return
     */
    public  String initToken (User user){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        long nowMillis = System.currentTimeMillis();//生成JWT的时间
        Date now = new Date(nowMillis);
        Map<String,Object> claims = new HashMap<String,Object>();//创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        claims.put("u", user.getU());
        claims.put("n", user.getNickname());
        SecretKey key = generalKey();//生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        //下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder() //这里其实就是new一个JwtBuilder，设置jwt的body
                .setClaims(claims)          //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                //.setId(UUID.randomUUID().toString())                  //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(now)           //iat: jwt的签发时间
                .setIssuer("zzj")
                .setSubject(user.getId())        //sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .signWith(signatureAlgorithm, key);//设置签名使用的签名算法和签名使用的秘钥
        if (timeout >= 0) {
            long expMillis = nowMillis + timeout*1000;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);     //设置过期时间
        }
        return builder.compact();           //就开始压缩为xxxxxxxxxxxxxx.xxxxxxxxxxxxxxx.xxxxxxxxxxxxx这样的jwt
    }

    public  Claims getKey(String token){
        SecretKey key = generalKey();
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claims;
    }
    public  Odin checkToken(UserService userService,String token){
        if(StringUtils.isEmpty(token)){
            return new Odin(Hela.AuthLess);
        }
        try {
            Claims claims = getKey(token);
            String userID = claims.get("sub", String.class);
            //自动刷新
            Date invalidDate = claims.getExpiration();
            Date now = new Date();
            long spacing = invalidDate.getTime() - now.getTime();
            if(spacing <= refreshsinterval*1000){
                log.info(token);
                token = refreshToken(userService,userID);
                log.info(token);
                return new Odin(Hela.AUTOREFRESHTOKEN,token);
            }
            return new Odin(Hela.SUCCESS,userID);
        } catch (ExpiredJwtException e) {
            // 在解析JWT字符串时，如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常，说明本次请求已经失效
//            e.printStackTrace();
            log.info("token Timeout:" + token);
            return new Odin(Hela.TimeOut);
        } catch (SignatureException e) {
            // 在解析JWT字符串时，如果密钥不正确，将会解析失败，抛出SignatureException异常，说明该JWT字符串是伪造的
            log.info("discorrect token:" + token);
            return new Odin(Hela.WrongKey);
        } catch (Exception e) {
            log.info(e.getMessage(),e);
            return new Odin(Hela.IllegalRequest,e);
        }
    }
    public  String refreshToken(UserService userService,String userID){
        User user = userService.getById(userID);
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        long nowMillis = System.currentTimeMillis();//生成JWT的时间
        Date now = new Date(nowMillis);
        Map<String,Object> claims = new HashMap<String,Object>();//创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        claims.put("u", user.getU());
        claims.put("n", user.getNickname());
        SecretKey key = generalKey();//生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        //下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder() //这里其实就是new一个JwtBuilder，设置jwt的body
                .setClaims(claims)          //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                //.setId(UUID.randomUUID().toString())                  //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(now)           //iat: jwt的签发时间
                .setIssuer("zzj")
                .setSubject(user.getId())        //sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .signWith(signatureAlgorithm, key);//设置签名使用的签名算法和签名使用的秘钥
        if (timeout >= 0) {
            long expMillis = nowMillis + timeout*1000;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);     //设置过期时间
        }
        return builder.compact();           //就开始压缩为xxxxxxxxxxxxxx.xxxxxxxxxxxxxxx.xxxxxxxxxxxxx这样的jwt
    }

    public String getUserID(String token){
        String userID = "";
        Claims claims = getKey(token);
        userID = claims.get("sub", String.class);
        return userID;
    }
}
