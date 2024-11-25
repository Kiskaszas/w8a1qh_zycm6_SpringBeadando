package org.example.kaszmaginnovate.controller;

import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class ResponseUtil {
    public static <T> ResponseEntity<T> toResponseEntity(Optional<T> optional) {
        return optional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
