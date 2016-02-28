package com.mindex.autorest.service;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public abstract class AbstractBaseHttpTest {
	private static final String basePath = "http://localhost:8080/autorest-rev/";

	public RestTemplate createRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		setMessageConverter(restTemplate);
		return restTemplate;
	}
	
	public RestTemplate createRestTemplateWithHttpClientFactory(ClientHttpRequestFactory factory) {
		RestTemplate restTemplate = new RestTemplate(factory);
		setMessageConverter(restTemplate);
		return restTemplate;
	}

	private void setMessageConverter(RestTemplate restTemplate) {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
		jsonMessageConverter.setObjectMapper(getObjectMapper());
		messageConverters.add(jsonMessageConverter);
		restTemplate.setMessageConverters(messageConverters);
	}

	private ObjectMapper getObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("MM/dd/yyyy"));
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper;
	}

	protected String buildURL(String uri) throws URISyntaxException {
		return basePath.concat(uri);
	}
}
