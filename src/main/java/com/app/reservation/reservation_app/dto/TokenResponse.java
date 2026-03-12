package com.app.reservation.reservation_app.dto;

public class TokenResponse {
    private String accesToken;
    private String refreshToken;

    public TokenResponse(String accesToken, String refreshToken) {
        this.accesToken = accesToken;
        this.refreshToken = refreshToken;
    }
    public String getAccesToken() {
        return accesToken;
    }
    public void setAccesToken(String accesToken) {
        this.accesToken = accesToken;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
