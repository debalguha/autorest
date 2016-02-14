package me.mindex.autorest.web;

import java.util.Collection;
import java.util.Date;

import me.mindex.autorest.model.YtmChannelVideo;
import me.mindex.autorest.service.VideoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

@Controller
@RequestMapping("AutoScanner")
public class VideoController {
	@Autowired
	private VideoService videoService;
	
	@RequestMapping(value = "savevideo", method = RequestMethod.POST)
	public @ResponseBody ServiceStatus saveVideo(@ModelAttribute YtmChannelVideo video){
		video.setEntryDate(new Date());
		videoService.saveVideo(video);
		return ServiceStatus.SUCCESS;
	}
	
	@RequestMapping(value = "videosCode", method = RequestMethod.GET)
	public @ResponseBody Collection<String> getVideosCode(@RequestParam(value = "channelId", required = false) String channelId){
		return Strings.isNullOrEmpty(channelId)?videoService.getAllVideoCodes():videoService.getAllVideoCodes(channelId);
	}
	
	@ExceptionHandler(Throwable.class)
	public @ResponseBody ServiceStatus handleException(Throwable t){
		return ServiceStatus.FAILURE;
	}
	
}
