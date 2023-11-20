package com.traveldreams.service;

import com.traveldreams.entity.RefreshToken;
import com.traveldreams.entity.UserEntity;
import com.traveldreams.repository.RefreshTokenRepository;
import com.traveldreams.request.RefreshTokenRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {


    private UserService userService;
    private RefreshTokenRepository refreshTokenRepository;
    private JwtService jwtService;

    @Value("${jwt.refreshTokenExpirationTimeInMillis}")
    private Long refreshTokenExpirationTimeInMillis;

    public RefreshTokenService(UserService userService, RefreshTokenRepository refreshTokenRepository, JwtService jwtService) {
        super();
        this.userService = userService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtService = jwtService;
    }


    public RefreshToken generateRefreshToken (Long userId) {
        Optional<UserEntity> userOpt = userService.findById(userId);

        if (userOpt.isPresent()) {
            Optional<RefreshToken> refreshTokenOpt= refreshTokenRepository.findById(userId);

            RefreshToken refreshToken = null;

            if (refreshTokenOpt.isPresent()) {
                refreshToken = refreshTokenOpt.get();
                refreshToken.setExpiration(generateRefreshTokenExpirationDate());
                refreshToken.setRefreshToken(generateRandomTokenValue());
            } else {
                refreshToken = new RefreshToken(userOpt.get(), generateRandomTokenValue(), generateRefreshTokenExpirationDate());
            }
//            refreshToken.setUser(userOpt.get());
            refreshToken = refreshTokenRepository.save(refreshToken);
            return refreshToken;
        }
        return null;
    }


    private String generateRandomTokenValue() {
        return UUID.randomUUID().toString();
    }


    private Date generateRefreshTokenExpirationDate() {
        return new Date(System.currentTimeMillis() + refreshTokenExpirationTimeInMillis);
    }


    public String generateNewAccessToken(RefreshTokenRequest refreshTokenRequest) {
        Optional<RefreshToken> refreshTokenOpt = refreshTokenRepository.findByRefreshToken(refreshTokenRequest.refreshToken());
        // TODO: write code to check that the refreshtoken hasn't expired

        String accessToken;
        accessToken = refreshTokenOpt.map(RefreshTokenService::isNonExpired)
                .map(refreshToken -> jwtService.generateToken(new HashMap<>(), refreshToken.getUser()))
                .orElseThrow(() -> new IllegalArgumentException("Refresh Token Not Found"));

        return accessToken;
    }

    private static RefreshToken isNonExpired (RefreshToken refreshToken) {
        if (refreshToken.getExpiration().after(new Date())){
            return refreshToken;
        } else {
            throw new IllegalArgumentException("Refresh Token has expired");
        }
    }

}
