package com.traveldreams.security;

import com.traveldreams.entity.UserEntity;
import com.traveldreams.request.RefreshTokenRequest;
import com.traveldreams.service.JwtService;
import com.traveldreams.service.RefreshTokenService;
import com.traveldreams.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.traveldreams.utils.CookieUtils;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UserService userService;
    private RefreshTokenService refreshTokenService;

    public JwtAuthenticationFilter(JwtService jwtService, UserService userService, RefreshTokenService refreshTokenService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        /**
         * Requests : - Headers -> key/value (Authorization -> Bearer xxx.yyy.zzz) -
         * Body -> (if JSON) key/value pairs -
         */

        Cookie accessTokenCookie = null;
        Cookie refreshTokenCookie = null;

        if (request.getCookies() != null) {
            for (Cookie cookie: request.getCookies()) {
                if (cookie.getName().equals(CookieUtils.ACCESS_TOKEN_NAME)) {
                    accessTokenCookie = cookie;
                } else if (cookie.getName().equals(CookieUtils.REFRESH_TOKEN_NAME)){
                    refreshTokenCookie = cookie;
                }
            }
        };

        if (accessTokenCookie != null) {
            /*
              hey, we have a token in the request lets see if it's valid with our JwtService
             */
            int loginTryCount = 0;

            while (loginTryCount <= 2) {
                String token = accessTokenCookie.getValue();
                try {
                    String subject = jwtService.getSubject(token);

                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                    if (StringUtils.hasText(subject) && authentication == null) {

                        UserDetails userDetails = (UserEntity) userService.loadUserByUsername(subject);

                        if (jwtService.isValidToken(token, userDetails)) {

                            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                                    userDetails.getPassword(),
                                    userDetails.getAuthorities());
                            securityContext.setAuthentication(authToken);
                            SecurityContextHolder.setContext(securityContext);
                        }
                    }
                } catch (ExpiredJwtException e) {
                    // ignore this error for now
                    try {
                        token = refreshTokenService.generateNewAccessToken(new RefreshTokenRequest(refreshTokenCookie.getValue()));
                        accessTokenCookie = CookieUtils.createAccessTokenCookie(token);

                        response.addCookie(accessTokenCookie);
                    } catch (Exception e1) {
                        // there was a problem generating a new access token
                        // we are ignoring this error on purpose in order to allow
                        // the flow of the filterChain to continue
                    }
                }
                loginTryCount++;
            }

        }
        filterChain.doFilter(request, response);

    }

}