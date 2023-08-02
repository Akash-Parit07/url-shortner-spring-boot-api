package com.urlshortner.UrlShorten.logic;

import java.nio.charset.Charset;

import org.apache.commons.validator.routines.UrlValidator;

import com.google.common.hash.Hashing;

public class GenerateShortUrl {
	
	public static String getShortUrl(String url) {
		return Hashing.murmur3_32().hashString(url, Charset.defaultCharset()).toString();
	}
	
	public static boolean isValidUrl(String url) {
		return UrlValidator.getInstance().isValid(url);
	}
}
