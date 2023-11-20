package com.traveldreams.security;

import com.traveldreams.entity.RefreshToken;
import com.traveldreams.entity.UserEntity;
import com.traveldreams.service.JwtService;
import com.traveldreams.service.RefreshTokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.traveldreams.utils.CookieUtils;

import java.io.IOException;
import java.util.HashMap;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public LoginSuccessHandler(JwtService jwtService, RefreshTokenService refreshTokenService) {
        super();
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        UserEntity user = (UserEntity) authentication.getPrincipal();

        String accessToken = jwtService.generateToken(new HashMap<>(), user);
        RefreshToken refreshToken = refreshTokenService.generateRefreshToken(user.getId());

        Cookie accessTokenCookie = CookieUtils.createAccessTokenCookie(accessToken);
        Cookie refreshTokenCookie = CookieUtils.createRefreshTokenCookie(refreshToken.getRefreshToken());

        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);
        response.sendRedirect("/countries");

    }

}

