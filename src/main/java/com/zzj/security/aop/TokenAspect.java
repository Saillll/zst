package com.zzj.security.aop;

import com.zzj.common.Hela;
import com.zzj.common.Odin;
import com.zzj.security.jwt.JWTUtil;
import com.zzj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class TokenAspect {

    @Autowired
    private UserService userService;
    @Around(value = "@annotation(com.zzj.security.aop.TokenCut)")
    public Odin TokenCheck(ProceedingJoinPoint joinPoint){
        boolean go = true;
        boolean refresh = false;
        Odin odin = new Odin(Hela.SUCCESS);
        String token = "";
        Object objects[] = joinPoint.getArgs();
        MethodSignature singnature = (MethodSignature)joinPoint.getSignature();
        Method method = singnature.getMethod();
        TokenCut tokenCut = method.getAnnotation(TokenCut.class);
        boolean required =  tokenCut.required();
        if(required){
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = ObjectUtils.isEmpty(request) ? "":request.getHeader("Authorization");
            odin = JWTUtil.getInstance().checkToken(userService,token);
        }
//本方法 必须在参数中传入HttpServletRequest，而如果采用上面的方法，则可以直接在jp中获取
//        if(required){  //token是必须项，进行判断
//            HttpServletRequest request = null;
//            for(Object object:objects){
//                if(object instanceof HttpServletRequest){
//                    request = (HttpServletRequest)object;
//                    token = request.getHeader("Authorization");
//                }
//            }
//        }
        if(odin.getHela() != Hela.SUCCESS ){
            if(odin.getHela() == Hela.AUTOREFRESHTOKEN){
                token = String.valueOf(odin.getLoki()); //新token
                refresh = true;
            }else {
                go = false;
            }
        }
        if(odin.getHela() == Hela.AUTOREFRESHTOKEN){
            refresh = true;
        }
        try{
            if(go){
                log.info("success Token is:" + token);
                odin = (Odin)joinPoint.proceed();
                if(refresh){
                    odin.setThor(token);
                    if(odin.getHela() == Hela.SUCCESS){
                        odin.setHela(Hela.AUTOREFRESHTOKEN);
                    }
                }
            }
        }catch (Exception e){
            log.info(e.getMessage(),e);
            return new Odin(Hela.AuthLess,e);
        }catch (Throwable t) {
            log.info(t.getMessage(),t);
            return new Odin(Hela.AuthLess,t);
        } finally {
            log.info("Check Token Done");
        }
        return odin;
    }
}
