package com.pms.filter;


import com.pms.enums.HttpCodeEnum;
import com.pms.exception.PMSException;
import com.pms.rest.JwtProperties;
import com.pms.rest.JwtTokenUtil;
import com.pms.utils.RenderUtils;
import io.jsonwebtoken.JwtException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 对客户端请求的jwt token验证过滤器
 */
public class AuthFilter extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //登录地址不做校验
        if (request.getServletPath().equals("/" + jwtProperties.getAuthPath())) {
            chain.doFilter(request, response);
            return;
        }
        final String requestHeader = request.getHeader(jwtProperties.getHeader());
        String authToken = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7);

            //验证token是否过期,包含了验证jwt是否正确
            try {
                boolean flag = jwtTokenUtil.isTokenExpired(authToken);
                if (flag) {
                    RenderUtils.renderJson(response, new PMSException(HttpCodeEnum.TOKEN_ERR));
                    return;
                }
            } catch (JwtException e) {
                //有异常就是token解析失败
                RenderUtils.renderJson(response, new PMSException(HttpCodeEnum.TOKEN_ERR));
                return;
            }
        } else {
            //header没有带Bearer字段
            RenderUtils.renderJson(response, new PMSException(HttpCodeEnum.TOKEN_ERR));
            return;
        }
        chain.doFilter(request, response);
    }
}