package ru.kata.spring.boot_security_bootstrap.demo.configs;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security_bootstrap.demo.details.UserDetailsImp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {
    // Spring Security использует объект Authentication, пользователя авторизованной сессии.
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        UserDetailsImp userDetailsImp = (UserDetailsImp) authentication.getPrincipal();
        String redirectURL = httpServletRequest.getContextPath();

        if (userDetailsImp.hasRole("ROLE_ADMIN")) {
            redirectURL = "/admin";
        } else if (userDetailsImp.hasRole("ROLE_USER")) {
            redirectURL = "/user";
        } else {
            redirectURL = "/";
        }

        httpServletResponse.sendRedirect(redirectURL);
    }
}