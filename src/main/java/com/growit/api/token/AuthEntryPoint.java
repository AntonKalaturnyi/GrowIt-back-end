package com.growit.api.token;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(MessageResponse
                .builder()
                .url(req.getRequestURI())
                .message(authException.getLocalizedMessage())
                .build());
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.setStatus(403);
        res.getWriter().write(json);
    }
}
