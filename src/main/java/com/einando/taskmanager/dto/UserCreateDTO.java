package com.einando.taskmanager.dto;

import com.einando.taskmanager.utils.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashSet;
import java.util.Set;

@Schema(name = "UserCreateDTO", description = "Objeto para criação de novo usuário")
public class UserCreateDTO {

    @Schema(description = "Nome de usuário (login)", example = "einand0")
    private String username;

    @Schema(description = "Email do usuário", example = "einando@example.com")
    private String email;

    @Schema(description = "Senha do usuário", example = "senhaSegura123")
    private String password;

    @Schema(description = "Papéis atribuídos ao usuário", example = "[\"USER\"]")
    private Set<UserRole> roles = new HashSet<>();

    public UserCreateDTO() {}

    public UserCreateDTO(String username, String email, String password, Set<UserRole> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }
}
