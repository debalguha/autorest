package me.mindex.autorest.web;

import me.mindex.autorest.model.YtmWordListEntity;
import me.mindex.autorest.service.WordListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("AutoScanner")
public class WordListController {
	@Autowired
	private WordListService wordListService;
	
	@RequestMapping(value = "getword", method = RequestMethod.GET)
	public @ResponseBody YtmWordListEntity getWord(){
		return wordListService.getOneAvailableWord();
	}
	
	@RequestMapping(value = "wordcompleted", method = RequestMethod.GET)
	public @ResponseBody String wordCompleted(@RequestParam("ywlId") Integer word){
		return wordListService.wordCompleted(word)!=null?"0":"ERROR";
	}
	
}
