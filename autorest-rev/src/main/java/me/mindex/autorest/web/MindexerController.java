package me.mindex.autorest.web;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import me.mindex.autorest.model.YtmChannelVideo;
import me.mindex.autorest.model.YtmChannelsScan;
import me.mindex.autorest.model.YtmRegexTemplate;
import me.mindex.autorest.service.RegexService;
import me.mindex.autorest.service.VideoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("mindexer")
public class MindexerController {
	@Autowired
	private RegexService service;
	
	@Autowired
	private VideoService videoService;
	
	@RequestMapping(value = "RegexTamplates", method = RequestMethod.GET)
	public @ResponseBody Collection<YtmRegexTemplate> findAll(){
		return service.findAllRegexTemplates();
	}
	
	@RequestMapping(value = "videosCode", method = RequestMethod.GET)
	public @ResponseBody Collection<String> getVideosCode(){
		return videoService.getAllVideoCodes();
	}
	
	@RequestMapping(value = "generalanalytics", method = RequestMethod.GET)
	public @ResponseBody List<Map<?, ?>> generateAnalytics(@RequestParam("typeid") int type){
		return type==1?videoService.getYearLyVideoStatistics():videoService.getCategoricalVideoUploads();
	}
	
	@RequestMapping(value = "getchannels", method = RequestMethod.GET)
	public @ResponseBody Collection<YtmChannelsScan> getChannels(@RequestParam("typeid") int type){
		return type==-1?sortByVideoCountDesc(videoService.getChannelsWithVideoCountGT(0)):type==100?sortByVideoCountDesc(videoService.getChannelsWithVideoCountGT(0)):type==500?sortByVideoCountDesc(videoService.getChannelsWithVideoCountGT(0)):sortByVideoCountDesc(videoService.getChannelsWithVideoCountGT(0));
	}
	
	@RequestMapping(value = "getchannelsvideo", method = RequestMethod.GET)
	public @ResponseBody Collection<YtmChannelVideo> getChannelVideos(@RequestParam("channelid") String channelId, @RequestParam("start") int start){
		return videoService.getVideosForChannel(channelId, start);
	}
	
	@RequestMapping(value = "deletevideoschannel", method = RequestMethod.GET)
	public @ResponseBody ServiceStatus deleteVideoFromChannel(@RequestParam("channelid") String channelId){
		videoService.deleteChannelVideos(channelId);
		return ServiceStatus.SUCCESS;
	}
	
	private Collection<YtmChannelsScan> sortByVideoCountDesc(Collection<YtmChannelsScan> channels) {
		return channels.stream().sorted().collect(Collectors.toList());
	}

	@ExceptionHandler(Throwable.class)
	public @ResponseBody ServiceStatus handleException(Throwable t){
		return ServiceStatus.FAILURE;
	}
}
