package com.mindex.autorest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

import me.mindex.autorest.model.YtmChannelVideo;
import me.mindex.autorest.model.YtmChannelsScan;
import me.mindex.autorest.model.YtmRegexTemplate;
import me.mindex.autorest.web.ServiceStatus;

public class MindexerControllerTestWithHttp extends AbstractBaseHttpTest{
	
	@Test
	public void shouldBeAbleToFindAllRegexTemplates() throws Exception {
		assertFalse(CollectionUtils.isEmpty(createRestTemplate().exchange(buildURL("mindexer/RegexTamplates"), HttpMethod.GET, null, new ParameterizedTypeReference<List<YtmRegexTemplate>>() {}).getBody()));
	}
	
	@Test
	public void shouldBeAbleToGenerateAnalyticsWithType1() throws Exception {
		assertFalse(CollectionUtils.isEmpty(createRestTemplate().exchange(UriComponentsBuilder.fromHttpUrl(buildURL("mindexer/generalanalytics")).queryParam("typeid", "1").build().toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<List<List<String>>>() {}).getBody()));
	}
	
	@Test
	public void shouldBeAbleToGenerateAnalyticsWithType2() throws Exception {
		assertFalse(CollectionUtils.isEmpty(createRestTemplate().exchange(UriComponentsBuilder.fromHttpUrl(buildURL("mindexer/generalanalytics")).queryParam("typeid", "2").build().toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<List<List<String>>>() {}).getBody()));
	}
	
	@Test
	public void shouldBeAleToGetChannels() throws Exception {
		assertFalse(CollectionUtils.isEmpty(createRestTemplate().exchange(UriComponentsBuilder.fromHttpUrl(buildURL("mindexer/getchannels")).queryParam("typeid", "-1").build().toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<List<YtmChannelsScan>>() {}).getBody()));
	}
	
	@Test
	public void shouldBeAbleToGetVideosForChannel() throws Exception {
		YtmChannelsScan channel = createRestTemplate().getForObject(buildURL("AutoScanner/getchannel"), YtmChannelsScan.class);
		if(channel.getVideosCount()>0)
			assertFalse(CollectionUtils.isEmpty(createRestTemplate().exchange(UriComponentsBuilder.fromHttpUrl(buildURL("mindexer/getchannelsvideo")).queryParam("channelid", channel.getChannelId()).queryParam("start", 1).build().toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<List<YtmChannelVideo>>() {}).getBody()));
		else
			assertTrue(CollectionUtils.isEmpty(createRestTemplate().exchange(UriComponentsBuilder.fromHttpUrl(buildURL("mindexer/getchannelsvideo")).queryParam("channelid", channel.getChannelId()).queryParam("start", 1).build().toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<List<YtmChannelVideo>>() {}).getBody()));
	}
	
	@Test
	public void shouldBeAbleToDeleteVideosOfAChannel() throws Exception{
		YtmChannelsScan channel = null;
		do{
			channel = createRestTemplate().getForObject(buildURL("AutoScanner/getchannel"), YtmChannelsScan.class);
		}while(channel.getVideosCount()<=0);
		assertEquals(ServiceStatus.SUCCESS, createRestTemplate().exchange(UriComponentsBuilder.fromHttpUrl(buildURL("mindexer/deletevideoschannel")).queryParam("channelid", channel.getChannelId()).build().toUri(), HttpMethod.GET, null, ServiceStatus.class).getBody());
	}
}
