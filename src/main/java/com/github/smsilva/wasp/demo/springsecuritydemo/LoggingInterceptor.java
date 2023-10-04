package com.github.smsilva.wasp.demo.springsecuritydemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("Request:");
        StringBuilder builder = new StringBuilder();
        addMethod(request, builder);
        addHeaders(request, builder);
        addBody(request, builder);
        System.out.println(builder.toString());
        return true;
    }

    private void addMethod(HttpServletRequest request, StringBuilder builder) {
        builder.append("%s %s\n".formatted(request.getMethod(), request.getRequestURI()));
    }

    private void addHeaders(HttpServletRequest request, StringBuilder builder) {
        builder.append("headers:\n");

        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            builder.append("  %s: %s\n".formatted(headerName, request.getHeader(headerName)));
        });
    }
    
    private void addBody(HttpServletRequest request, StringBuilder builder) {
        String body = getBody(request);

        if (body != null && !"".equals(body)) {
            builder.append("body:\n");
            builder.append(body);
        }
    }

    private String getBody(HttpServletRequest request) {
        String contentType = request.getContentType();
        MediaType type = contentType != null ? MediaType.valueOf(contentType) : null;

        try {
            if (type != null && type.equals(MediaType.APPLICATION_JSON)) {
                ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
                JsonNode jsonNode = objectMapper.readTree(serverHttpRequest.getBody());           
                return jsonNode.toPrettyString();
            }
        } catch (Exception e) {
            return e.getMessage();
        }

        return "";
    }

}
