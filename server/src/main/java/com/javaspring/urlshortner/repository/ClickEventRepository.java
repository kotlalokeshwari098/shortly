package com.javaspring.urlshortner.repository;

import com.javaspring.urlshortner.models.ClickEvent;
import com.javaspring.urlshortner.models.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ClickEventRepository extends JpaRepository<ClickEvent,Long> {
    List<ClickEvent> findByUrlMappingAndClickDateBetween(UrlMapping urlMapping,
                                                         LocalDateTime start,
                                                         LocalDateTime end);
    List<ClickEvent> findByUrlMappingInAndClickDateBetween(List<UrlMapping> urlMapping,
                                                         LocalDateTime start,
                                                           LocalDateTime end);

}
