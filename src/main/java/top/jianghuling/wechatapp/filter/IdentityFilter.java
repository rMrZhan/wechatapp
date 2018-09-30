package top.jianghuling.wechatapp.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;
import top.jianghuling.wechatapp.utils.SecurityUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;




public class IdentityFilter implements Filter {

    @Autowired
    private SecurityUtil securityUtil;

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init 启动");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String secretId = request.getParameter("secretId");
        System.out.println("doFilter调用");
        String openId = securityUtil.getUserId(secretId);
        request.setAttribute("userId",openId);
        request.removeAttribute("secretId");
        filterChain.doFilter(request,response);

    }


    }
