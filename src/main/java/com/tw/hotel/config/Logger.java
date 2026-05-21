package com.tw.hotel.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Logger implements Filter {
  private static final org.slf4j.Logger log = LoggerFactory.getLogger(Logger.class);

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    log.info("-> {} {} ", req.getMethod(), req.getRequestURI());
    chain.doFilter(request, response);
  }
}
