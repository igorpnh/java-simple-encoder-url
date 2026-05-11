package com.example.url_shortener.controller;

import com.example.url_shortener.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.Map;

@RestController
public class UrlController {

    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    @PostMapping("/shorten")
    public ResponseEntity<?> shorten(@RequestBody Map<String, String> body) {

        String originalUrl = body.get("originalUrl");
        String code = service.shortenUrl(originalUrl);

        return ResponseEntity.ok(Map.of("shortURL: ", "http://localhost:8080/" + code));
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> redirect(@PathVariable String code) {
        String originalUrl = service.gerOriginalUrl(code);

        return ResponseEntity.status(302).location(URI.create(originalUrl)).build();
    }
}
