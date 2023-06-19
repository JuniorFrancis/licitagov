package com.exame.licitagov.models.responses;

public class AuthenticationResponse {

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class Builder {

        public String token;

        public Builder withToken(String token) {
            this.token = token;
            return this;
        }

        public AuthenticationResponse build(){
            return new AuthenticationResponse(this.token);
        }
    }
}
