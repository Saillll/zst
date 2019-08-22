package com.zzj.security.aop;


import java.lang.annotation.*;

@Target(value = ElementType.METHOD) //  方法级别
@Retention(value = RetentionPolicy.RUNTIME) //  运行时
@Inherited
@Documented
/** 验证Token
 *
 */
public @interface TokenCut {


    /**
     * 是否必须有token,默认不需要
     * @return true必须拥有token,false不需要token
     */
    public boolean required() default false;

}
