package com.einando.taskmanager.dto;

import com.einando.taskmanager.entities.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UserResponseDTO", description = "Dados públicos do usuário retornados pela API")
public class UserResponseDTO {

    @Schema(description = "ID do usuário", example = "1")
    private Long id;

    @Schema(description = "Nome de usuário (login)", example = "einand0")
    private String username;

    @Schema(description = "Email do usuário", example = "einando@example.com")
    private String email;

    public UserResponseDTO() {}

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
