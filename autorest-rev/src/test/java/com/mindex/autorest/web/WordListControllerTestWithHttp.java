package com.mindex.autorest.web;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import me.mindex.autorest.model.YtmWordListEntity;

public class WordListControllerTestWithHttp extends AbstractBaseHttpTest{
	@Test
	public void shouldBeAbleToGetAWord() throws Exception {
		System.out.println(createRestTemplate().getForObject("http://localhost:8080/autorest-rev/AutoScanner/getword", YtmWordListEntity.class));
		System.out.println(createRestTemplate().getForObject("http://localhost:8080/autorest-rev/AutoScanner/getword", YtmWordListEntity.class));
		Assert.assertNotNull(createRestTemplate().getForObject("http://localhost:8080/autorest-rev/AutoScanner/getword", YtmWordListEntity.class));
	}
	
	@Test
	public void shouldBeAbleToGetAWordFromHosted() throws Exception {
		System.out.println(new Date());
		for(int i=0;i<100;i++)
			System.out.println(createRestTemplate().getForEntity("http://mindex.me:8080/autorest-rev/AutoScanner/getword", String.class).getBody());
		System.out.println(new Date());
	}
}
