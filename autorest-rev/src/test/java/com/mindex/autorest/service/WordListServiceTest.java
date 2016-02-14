package com.mindex.autorest.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import me.mindex.autorest.model.YtmWordListEntity;
import me.mindex.autorest.service.WordListService;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
 @ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class WordListServiceTest {
	@Autowired
	private WordListService wordListService;

/*	@Test
	@Ignore
	public void shouldBeAbleToPopulateWordListTableFromFile() throws Exception {
		String filePath = "C:\\DebWork\\AutoMindexerREST\\dict.txt";
		@SuppressWarnings("unchecked")
		Collection<YtmWordListEntity> wordList = ((List<String>) FileUtils.readLines(new File(filePath))).stream().parallel().map(new Function<String, YtmWordListEntity>() {
			@Override
			public YtmWordListEntity apply(String t) {
				if (t.length() > 45)
					System.out.println("Long word:: " + t);
				return new YtmWordListEntity(t.trim(), false, false);
			}
		}).collect(Collectors.toSet());
		wordListService.saveWordList(wordList);
	}*/

	@Test
	public void shouldBeAbleToGetOneAvailableWord() throws Exception {
		Assert.assertNotNull(wordListService.getOneAvailableWord());
	}
	
	@Test
	public void shouldBeAbleToCompleteAWord() throws Exception {
		YtmWordListEntity word = wordListService.getOneAvailableWord();
		Assert.assertTrue(wordListService.wordCompleted(word.getYwlId()).isComplete());
	}

	@Test
	@Ignore
	public void wordListServiceStressTest() throws Exception {
		RestTemplate template = new RestTemplate();
		Executor executor = Executors.newFixedThreadPool(500);
		for (int i = 0; i < 1000; i++) {
			executor.execute(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println(template.getForObject(new URI("http://localhost:8080/autorest-rev/AutoScanner/getword"), String.class));
					} catch (RestClientException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		Thread.sleep(100000);
	}
}
