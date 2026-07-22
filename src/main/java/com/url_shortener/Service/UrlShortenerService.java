package com.url_shortener.Service;

import com.url_shortener.Entity.ShortUrls;
import com.url_shortener.Repository.UrlRepository;
import com.url_shortener.Util.Base62Encoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UrlShortenerService {
    private final UrlRepository urlRepository;

    public String shortenUrl(String longUrl){
        ShortUrls entity=new ShortUrls();
        entity.setLong_url(longUrl);

        entity=urlRepository.save(entity);

        String shorturl=Base62Encoder.encode(entity.getId());

        entity.setShort_code(shorturl);
        urlRepository.save(entity);
        return shorturl;
    }
}
