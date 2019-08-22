package com.zzj.security.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by Administrator on 2018/8/13.
 */
@Configuration
public class ShiroConfig {



    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //======================拦截器. map
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/index", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("all"+"/**", "anon");
        filterChainDefinitionMap.put("/regist", "anon");
        filterChainDefinitionMap.put("admin"+"/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //======================拦截器. map

        //====================url
        //filterChainDefinitionMap.put("/**", "authc");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        //====================url

        //====================filters
        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        // 定制logout 过滤，指定注销后跳转到登录页(默认为根路径)
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/login");

        // 定制authc 过滤，指定登录表单参数
        //暂时没用
//        ShiroFormAuthenticationFilter authFilter = new ShiroFormAuthenticationFilter();
//        authFilter.setUsernameParam("username");
//        authFilter.setPasswordParam("password");
//        authFilter.setLoginUrl("/loginnn");
//        authFilter.setSuccessUrl("/");
//        filters.put("authc", authFilter);

        filters.put("logout", logoutFilter);
        shiroFilterFactoryBean.setFilters(filters);
        //====================filters

        return shiroFilterFactoryBean;
    }

    @Bean(name = "shiroReam")
    public ShiroRealm realm() {
        ShiroRealm shiroReam = new ShiroRealm();
        shiroReam.setCredentialsMatcher(matcher());
        return shiroReam;
    }
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("shiroReam")ShiroRealm shiroReam) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
//        securityManager.setCacheManager(shiroCacheManager());

//       securityManager.setSessionManager(shiroSessionManager());
        return securityManager;
    }

    //    @Bean
//    public ShiroSessionManager shiroSessionManager(){
//        ShiroSessionManager shiroSessionManager = new ShiroSessionManager();
//        //这里可以不设置。Shiro有默认的session管理。如果缓存为Redis则需改用Redis的管理
//        shiroSessionManager.setGlobalSessionTimeout(maxTime);//单位毫秒，60000=1min
//        shiroSessionManager.setSessionIdCookie(getSimpleCookie());
//        shiroSessionManager.setSessionIdCookieEnabled(true);
//
//        shiroSessionManager.setSessionDAO(shiroSessionDao());
//        shiroSessionManager.setSessionValidationScheduler(getExecutorServiceSessionValidationScheduler());
////        shiroSessionManager.setSessionIdCookie(new SimpleCookie("sh_cookie"));
////        shiroSessionManager.setSessionIdCookieEnabled(true);
//        return shiroSessionManager;
//    }
    @Bean
    public SimpleCookie getSimpleCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("zst");
        simpleCookie.setMaxAge(1800);//单位秒
        simpleCookie.setHttpOnly(true);
        return  simpleCookie;
    }
    @Bean
    public ExecutorServiceSessionValidationScheduler getExecutorServiceSessionValidationScheduler() {
        ExecutorServiceSessionValidationScheduler scheduler = new ExecutorServiceSessionValidationScheduler();
        scheduler.setInterval(900000);
        return scheduler;
    }

//    @Bean
//    public ShiroSessionDao shiroSessionDao(){
//        ShiroSessionDao shiroSessionDao = new ShiroSessionDao();
//        shiroSessionDao.setSessionIdGenerator(new JavaUuidSessionIdGenerator());
//        shiroSessionDao.setActiveSessionsCacheName("xiaojiba");
//        return new ShiroSessionDao();
//    }
//    @Bean
//    public ShiroUserManager userManager() {
//        return new ShiroUsxerManager(matcher());
//    }
//    @Bean
//    public ShiroCacheManager shiroCacheManager(){
//        return new ShiroCacheManager();
//    }
    @Bean
    public ShiroHashMatcher matcher() {
        return new ShiroHashMatcher();
    }

}
