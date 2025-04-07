package com.einando.taskmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UserLoginDTO", description = "Credenciais para login do usuário")
public class UserLoginDTO {

    @Schema(description = "Email cadastrado do usuário", example = "einando@example.com")
    private String email;

    @Schema(description = "Senha do usuário", example = "senhaSegura123")
    private String password;

    public UserLoginDTO() {}

    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
