package com.traveldreams.utils;

import jakarta.servlet.http.Cookie;
public class CookieUtils {

    public static final String REFRESH_TOKEN_NAME = "refreshToken";
    public static final String ACCESS_TOKEN_NAME = "accessToken";

    public static Cookie createAccessTokenCookie (String value) {

        return new Cookie(ACCESS_TOKEN_NAME, value);
    }

    public static Cookie createRefreshTokenCookie (String value) {

        return new Cookie(REFRESH_TOKEN_NAME, value);
    }
}
