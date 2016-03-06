package com.mindex.autorest.web;

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
}
