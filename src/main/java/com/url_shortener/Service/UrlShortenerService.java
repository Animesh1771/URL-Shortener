package com.url_shortener.Service;

import com.url_shortener.Entity.ShortUrls;
import com.url_shortener.Repository.UrlRepository;
import com.url_shortener.Util.Base62Encoder;
import com.url_shortener.Util.DataSourceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UrlShortenerService {
    private final UrlRepository urlRepository;
    private final CounterService counterService;

    public String shortenUrl(String longUrl){
        ShortUrls entity=new ShortUrls();
        String key = "id";
        long value = counterService.incrementCounter(key);
        log.info("value "+value);
        String shorturl= Base62Encoder.encode(value);
        entity.setShort_code(shorturl);
        entity.setLong_url(longUrl);
        long shard=value%3;
        try{
            if(shard==0){
                DataSourceContext.setDataSource("DB1");
            }
            else if(shard==1){
                DataSourceContext.setDataSource("DB2");
            }
            else{
                DataSourceContext.setDataSource("DB3");
            }
            urlRepository.save(entity);
            return "shard "+shard+" "+shorturl;
        }finally {
            DataSourceContext.clear();
        }
    }
}
