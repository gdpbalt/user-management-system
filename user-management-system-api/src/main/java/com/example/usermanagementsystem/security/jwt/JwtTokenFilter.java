package com.example.usermanagementsystem.security.jwt;

import com.example.usermanagementsystem.dto.ErrorMessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
@Log4j2()
public class JwtTokenFilter extends HttpFilter {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {
        try {
            String token = jwtTokenProvider.resolveToken(request);
            log.debug(request.getMethod() + " " + request.getRequestURL().toString()
                    + "?" + request.getQueryString());
            log.debug("Token: {}", token == null ? "not found" : token);
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            log.debug("Move to next filter");
            filterChain.doFilter(request, response);
        } catch (RuntimeException e) {
            log.debug("Has problem with token verified");
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            ErrorMessageDto errorResponse = new ErrorMessageDto(
                    HttpStatus.UNAUTHORIZED.value(), new Date(), e.getMessage(), "");
            response.getWriter().write(convertObjectToJson(errorResponse));
        }
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper()
                .findAndRegisterModules()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper.writeValueAsString(object);
    }
}
