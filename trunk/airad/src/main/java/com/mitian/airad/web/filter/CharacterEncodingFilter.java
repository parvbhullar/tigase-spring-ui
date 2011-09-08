package com.mitian.airad.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CharacterEncodingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String encoding = "UTF-8";
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        filterChain.doFilter(request, response);
        // String encoding = "GBK";
        // String ajaxRequestHeader = request.getHeader("X-Requested-With");
        // if ("XMLHttpRequest".equals(ajaxRequestHeader)) {
        // encoding = "UTF-8";
        // request.setAttribute("supportJs", "yes");
        // }
        // request.setCharacterEncoding(encoding);
        // System.err.println(request.getCharacterEncoding() + "encoding");
        // filterChain.doFilter(request, response);
    }

}
