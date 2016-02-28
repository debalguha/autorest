package com.mindex.autorest.service;

import static org.junit.Assert.assertFalse;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.mindex.autorest.service.VideoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class VideoServiceTest {
	@Autowired
	private VideoService videoService;
	
	@Test
	public void shouldBeAbleToGetYearLyVideoStatistics(){
		assertFalse(CollectionUtils.isEmpty(videoService.getYearLyVideoStatistics()));
	}
}
