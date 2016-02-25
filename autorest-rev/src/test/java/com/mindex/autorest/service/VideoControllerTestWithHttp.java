package com.mindex.autorest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Maps;

import me.mindex.autorest.model.YtmChannelVideo;
import me.mindex.autorest.model.YtmChannelsScan;
import me.mindex.autorest.web.ServiceStatus;

public class VideoControllerTestWithHttp extends AbstractBaseHttpTest{
	@Test
	public void shouldBeAbleToSaveVideo() throws Exception {
		RestTemplate restTemplate = createRestTemplate();
		YtmChannelsScan channelsScan = restTemplate.getForObject(buildURL("AutoScanner/getchannel"), YtmChannelsScan.class);
		assertEquals(ServiceStatus.SUCCESS, restTemplate.postForObject(buildURL("AutoScanner/savevideo"), new YtmChannelVideo(Short.valueOf("0"), createRandomJson(), new Date(), String.valueOf(RandomUtils.nextLong()), String.valueOf(RandomUtils.nextLong()), new YtmChannelsScan(channelsScan.getChannelId())), ServiceStatus.class));
	}
	
	@Test
	public void shouldBeAbleToGetVideosCode() throws Exception {
		RestTemplate restTemplate = createRestTemplate();
		assertFalse(CollectionUtils.isEmpty(restTemplate.exchange(buildURL("AutoScanner/videosCode"), HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {}).getBody()));
	}

	private String createRandomJson() throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, String> json = Maps.newHashMapWithExpectedSize(1);
		json.put("key", "value");
		StringWriter writer = new StringWriter();
		new ObjectMapper().writeValue(writer, json);
		return writer.toString();
	}

}
