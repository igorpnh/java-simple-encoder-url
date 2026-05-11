package com.example.url_shortener.service;

import com.example.url_shortener.entity.Url;
import com.example.url_shortener.repository.UrlRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UrlService {

    private final UrlRepository repository;

    public UrlService(UrlRepository urlRepository) {
        this.repository = urlRepository;
    }

    public String shortenUrl(String originalUrl) {
        String code = generateCode();

        Url url = Url.builder()
                .originalUrl(originalUrl)
                .shortCode(code)
                .build();

        repository.save(url);

        return code;
    }

    public String gerOriginalUrl(String code) {
        return repository.findByShortCode(code)
                .map(Url::getOriginalUrl)
                .orElseThrow(() -> new RuntimeException("Url not found"));
    }

    private @NonNull String generateCode() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return sb.toString();
    }

}
