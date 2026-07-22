package com.url_shortener.Repository;

import com.url_shortener.Entity.ShortUrls;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository <ShortUrls,Long> {
}
