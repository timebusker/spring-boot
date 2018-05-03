package cn.timebusker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @DESC:SpringSecurityConfig
 * @author: timebusker
 * @date:2018/5/3
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启@PreAuthorize注解
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UrlsDao urlsDao;
    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceBean());
        //密码MD5加密配置
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsServiceBean());
        authenticationProvider.setPasswordEncoder(new Md5PasswordEncoder());
        return authenticationProvider;
    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new CustomUserService(userInfoDao, urlsDao);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/js/**", "/css/**", "/images/**").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/user/list").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", false)
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/nolimit")
                .and()
                .httpBasic();
    }

    /**
     * 加载自定义过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(someFilter());
        registration.addUrlPatterns("/*");
//        registration.addInitParameter("paramName", "paramValue");
        registration.setName("commonFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean(name = "commonFilter")
    public Filter someFilter() {
        return new CommonFilter();
    }


}
