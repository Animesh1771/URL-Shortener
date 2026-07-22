package com.url_shortener.Controller;

import com.url_shortener.Service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/shorturl")
public class UrlShortenerController {
    private final UrlShortenerService urlShortenerService;

    @PostMapping("/generate")
    public String generateShortUrl(@RequestParam String long_url) {
        return urlShortenerService.shortenUrl(long_url);
    }
}
