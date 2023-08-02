package com.urlshortner.UrlShorten.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.urlshortner.UrlShorten.model.UrlModel;

public interface UrlRepository extends JpaRepository<UrlModel, Integer>{

	@Query(value = "SELECT DISTINCT originalUrl FROM UrlModel WHERE shortUrl = ?1")
	String findByShortUrl(String id);
}
