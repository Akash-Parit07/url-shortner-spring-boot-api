package com.urlshortner.UrlShorten.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urlshortner.UrlShorten.model.UrlModel;
import com.urlshortner.UrlShorten.service.UrlService;

@RestController
@RequestMapping("url/v1")
@CrossOrigin("http://localhost:4200/")
public class UrlController {
	@Autowired
	private UrlService urlService;
	
	
	@GetMapping("/{id}")
	public String getOriginalUrl(@PathVariable String id) {
		return urlService.getOriginalUrl(id);
	}
	
	@PostMapping("/convert")
	public UrlModel generatedShortUrl(@RequestBody String body) {
		return urlService.generatedShortUrl(body);
	}
}
