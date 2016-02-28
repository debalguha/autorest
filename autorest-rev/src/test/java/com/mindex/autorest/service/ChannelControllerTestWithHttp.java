package com.mindex.autorest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.StringUtils;

import me.mindex.autorest.model.YtmChannelsScan;
import me.mindex.autorest.web.ServiceStatus;

public class ChannelControllerTestWithHttp extends AbstractBaseHttpTest {
	@Test
	public void shouldBeAbleToFetchAllChannelIds() throws Exception {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				HttpClientBuilder.create().addInterceptorFirst(new HttpRequestInterceptor() {

					public void process(final HttpRequest request, final HttpContext context)
							throws HttpException, IOException {
						if (!request.containsHeader("Accept-Encoding")) {
							request.addHeader("Accept-Encoding", "gzip");
						}
					}

				}).build());
		List<String> channelIds = createRestTemplateWithHttpClientFactory(clientHttpRequestFactory)
				.exchange(buildURL("AutoScanner/channelList"), HttpMethod.GET, null,
						new ParameterizedTypeReference<List<String>>() {
						})
				.getBody();
		assertFalse(CollectionUtils.isEmpty(channelIds));
	}

	@Test
	public void shouldBeAbleToSaveChannel() throws Exception {
		assertEquals(ServiceStatus.SUCCESS,
				createRestTemplate().postForObject(buildURL("AutoScanner/saveChannel"),
						new YtmChannelsScan(String.valueOf(RandomUtils.nextLong()), new Date(), null, true,
								Short.valueOf("1"), false, String.valueOf(RandomUtils.nextInt()), 0,
								String.valueOf(RandomUtils.nextInt()), 0, 0, 0L, 0),
						ServiceStatus.class));
	}

	@Test
	public void shouldBeAbleToGetAChannel() throws Exception {
		assertFalse(StringUtils
				.isEmpty(createRestTemplate().getForObject(buildURL("AutoScanner/getchannel"), YtmChannelsScan.class)));
	}

	@Test
	public void shouldBeAbleToCompleteAChannel() throws Exception {
		YtmChannelsScan channel = createRestTemplate().getForObject(buildURL("AutoScanner/getchannel"),
				YtmChannelsScan.class);
		assertEquals(ServiceStatus.SUCCESS, createRestTemplate().postForObject(buildURL("AutoScanner/saveChannel"),
				channel.getChannelId(), ServiceStatus.class));
	}

	@Test
	public void shouldBeAbleToUpdateAChannel() throws Exception {
		YtmChannelsScan channel = createRestTemplate().getForObject(buildURL("AutoScanner/getchannel"),
				YtmChannelsScan.class);
		String googleIdOld = channel.getGoogleId();
		channel.setGoogleId(RandomStringUtils.randomAlphanumeric(10));
		assertEquals(ServiceStatus.SUCCESS, createRestTemplate().postForObject(buildURL("AutoScanner/updatechannel"),
				channel, ServiceStatus.class));
		channel = createRestTemplate().getForObject(buildURL("AutoScanner/channelById/{channelId}").toString(),
				YtmChannelsScan.class, channel.getChannelId());
		assertNotEquals(googleIdOld, channel.getGoogleId());
	}
}
