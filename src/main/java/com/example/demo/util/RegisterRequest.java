package com.example.demo.util;

public record RegisterRequest(
    String firstname,
    String lastname,
    String email,
    String password,
    String phone
) {
}
