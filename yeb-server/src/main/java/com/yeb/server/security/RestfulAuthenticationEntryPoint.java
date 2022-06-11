package com.yeb.server.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeb.server.common.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 *  自定义token失效或者用户未登录时的返回结果
 * </p>
 *
 * @author Eddie
 * @since 2022-01-25
 */
@Slf4j(topic = "RestfulAuthenticationEntryPoint Log")
@Component
public class RestfulAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        String header = request.getHeader("Authorization");
        log.info("header:{}",header);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        RespBean respBean = RespBean.error("没有访问权限！");
        respBean.setCode(401);
        out.write(new ObjectMapper().writeValueAsString(respBean));
        out.flush();
        out.close();
    }
}
