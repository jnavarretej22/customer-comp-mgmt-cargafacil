package com.cargafacil.dto;

public class AuthResponse {
	private String token;
    private String status;
    private int code; // Nuevo campo para el código de éxito

    public AuthResponse(String token, String status, int code) {
        this.token = token;
        this.status = status;
        this.code = code;
    }

    // Getters y Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
