package com.exame.licitagov.models.request;

public class AuthenticationRequest {

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthenticationRequest() {
    }

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class Builder {
        public String username;

        public String password;

        public void withUsername(String username){
            this.username = username;
        }

        public void withPassword(String password){
            this.password = password;
        }

        public AuthenticationRequest build() {
            return new AuthenticationRequest(username, password);
        }

    }


}
