package com.github.smsilva.wasp.demo.springsecuritydemo;

import static java.lang.System.lineSeparator;

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
        String methodLogLine = createMethodLineLog(request);

        LOGGER.info("HttpServletRequest: %s".formatted(methodLogLine));

        StringBuilder builder = new StringBuilder()
            .append(methodLogLine).append(lineSeparator())
            .append("headers:").append(lineSeparator())
            .append(createHeadersLinesLog(request)).append(lineSeparator())
            .append("body:").append(lineSeparator())
            .append(createBodyLineLog(request));
        
        LOGGER.trace(builder.toString());
        return true;
    }

    private String createMethodLineLog(HttpServletRequest request) {
        return "%s %s".formatted(request.getMethod(), request.getRequestURI());
    }

    private String createHeadersLinesLog(HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();

        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            builder.append("  %s: %s".formatted(headerName, request.getHeader(headerName)))
                .append(lineSeparator());
        });

        return builder.toString();
    }
    
    private String createBodyLineLog(HttpServletRequest request) {
        return extractBodyFrom(request);
    }

    private String extractBodyFrom(HttpServletRequest request) {
        MediaType type = getMediaTypeFrom(request);

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

    private MediaType getMediaTypeFrom(HttpServletRequest request) {
        String contentType = request.getContentType();
        
        MediaType type = contentType != null ? MediaType.valueOf(contentType) : null;

        return type;
    }

}
