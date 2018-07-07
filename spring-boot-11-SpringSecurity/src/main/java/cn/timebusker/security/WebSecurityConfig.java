package cn.timebusker.security;

import cn.timebusker.filter.AfterCsrfFilter;
import cn.timebusker.filter.BeforeLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    public void setAnyUserDetailsServiceImpl(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    /**
     * 匹配 "/" 路径，不需要权限即可访问
     * 匹配 "/user" 及其以下所有路径，都需要 "USER" 权限
     * 登录地址为 "/login"，登录成功默认跳转到页面 "/user"
     * 退出登录的地址为 "/logout"，退出成功后跳转到页面 "/login"
     * 默认启用 CSRF
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/header.html").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/user")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");

        // 在 UsernamePasswordAuthenticationFilter 前添加 BeforeLoginFilter
        http.addFilterBefore(new BeforeLoginFilter(), UsernamePasswordAuthenticationFilter.class);
        // 在 CsrfFilter 后添加 AfterCsrfFilter
        http.addFilterAfter(new AfterCsrfFilter(), CsrfFilter.class);
    }

    /**
     * 添加 UserDetailsService， 实现自定义登录校验
     */
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        // 自定义校验
        builder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
        // 基于内存校验
//        builder.inMemoryAuthentication().withUser("aaa").password("aaa");
//        builder.inMemoryAuthentication().withUser("bbb").password("bbb");
//        builder.inMemoryAuthentication().withUser("ccc").password("ccc");
    }

    /**
     * 设置密码加密
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
