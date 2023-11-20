package com.traveldreams.response;

public record AuthenticationResponse(String username, String accessToken, String refreshToken) {
}
