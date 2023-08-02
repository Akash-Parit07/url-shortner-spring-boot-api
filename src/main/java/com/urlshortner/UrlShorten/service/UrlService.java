package com.urlshortner.UrlShorten.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.urlshortner.UrlShorten.logic.GenerateShortUrl;
import com.urlshortner.UrlShorten.model.UrlModel;
import com.urlshortner.UrlShorten.repository.UrlRepository;

@Service
public class UrlService {
	@Autowired
	private UrlRepository urlRepository;
	
	public String getOriginalUrl(String id) {
//		System.out.println(id);
		String originalUrl = urlRepository.findByShortUrl(id);
//		UrlModel urlModel = new UrlModel();
//		if(originalUrl != null) {
//			urlModel.setOriginalUrl(originalUrl);
//			urlModel.setShortUrl(id);
//			urlModel.setStatus("Success");
//		}
//		else {
//			urlModel.setOriginalUrl(null);
//			urlModel.setShortUrl(id);
//			urlModel.setStatus("Not Found");
//		}
		
		if(originalUrl == null)
			return null;
		
		return originalUrl;
	}
	
	public UrlModel generatedShortUrl(String body) {
		ObjectMapper mapper = new ObjectMapper();
		String url = null;
        try {
        	UrlModel jsonUrl = mapper.readValue(body, UrlModel.class);
			url = jsonUrl.getOriginalUrl();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UrlModel urlModel = new UrlModel();
		if(GenerateShortUrl.isValidUrl(url)) {
			String shortUrl =  GenerateShortUrl.getShortUrl(url);
			urlModel.setOriginalUrl(url);
			urlModel.setShortUrl(shortUrl);
			urlModel.setStatus("Success");
			return urlRepository.save(urlModel);
		}
		else {
			urlModel.setOriginalUrl(url);
			urlModel.setShortUrl(null);
			urlModel.setStatus("Invalid Url");
		}
		return urlModel;
	}
}
