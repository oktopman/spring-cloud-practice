package me.oktop.zuulgateway.utils;

import me.oktop.zuulgateway.filters.FilterUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class UserContextFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        UserContextHolder.getContext().setCorrelationId(httpServletRequest.getHeader(UserContext.CORRELATION_ID));
        UserContextHolder.getContext().setUserId(httpServletRequest.getHeader(UserContext.USER_ID));
        UserContextHolder.getContext().setAuthToken(httpServletRequest.getHeader(UserContext.AUTH_TOKEN));
        UserContextHolder.getContext().setOrgId(httpServletRequest.getHeader(UserContext.ORG_ID));

        chain.doFilter(httpServletRequest, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
