package com.mindex.autorest.service;

import org.junit.Assert;
import org.junit.Test;

import me.mindex.autorest.model.YtmWordListEntity;

public class WordListControllerTestWithHttp extends AbstractBaseHttpTest{
	@Test
	public void shouldBeAbleToGetAWord() throws Exception {
		Assert.assertNotNull(createRestTemplate().getForObject("http://localhost:8080/autorest-rev/AutoScanner/getword", YtmWordListEntity.class));
	}
}
