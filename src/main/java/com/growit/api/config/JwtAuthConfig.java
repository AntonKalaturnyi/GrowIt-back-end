package com.growit.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.growit.api.token.MessageResponse;
import com.growit.api.token.TokenFilter;
import com.growit.api.token.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class JwtAuthConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>
                          implements AuthenticationEntryPoint {

    private final TokenProvider tokenProvider;

    @Autowired
    public JwtAuthConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        TokenFilter customFilter = new TokenFilter(tokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }

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
