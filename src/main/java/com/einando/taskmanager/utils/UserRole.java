package com.einando.taskmanager.utils;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Locale;

public enum UserRole {
    USER,
    ADMIN;

    @JsonCreator
    public static UserRole fromString(String value) {
        try {
            return UserRole.valueOf(value.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Role inválido: " + value + ". Use apenas USER ou ADMIN.");
        }
    }
}