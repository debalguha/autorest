package me.mindex.autorest.web;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import me.mindex.autorest.model.YtmChannelsScan;
import me.mindex.autorest.service.YtmChannelService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("AutoScanner")
public class ChannelController {
	private static final Logger LOG = LoggerFactory.getLogger(ChannelController.class);
	@Autowired
	private YtmChannelService channelService;
	private Lock channelLock = new ReentrantLock(true);
	
	@RequestMapping(value = "channelList", method = RequestMethod.GET)
	public @ResponseBody Collection<String> getAllChannelIds(){
		return channelService.getAllChannelIds();
	}
	
	@RequestMapping(value = "saveChannel", method = RequestMethod.POST)
	public @ResponseBody ServiceStatus saveChannel(@RequestBody YtmChannelsScan channel){
		channel.setVideosCount(0);
		channel.setEntryDate(new Date());
		channel.setProviderId((short) 1);
		channel.setScanComplete(false);
		channel.setIsWorking(false);
		channelService.saveChannel(channel);
		return ServiceStatus.SUCCESS;
	}
	
	@RequestMapping(value = "getchannel", method = RequestMethod.GET)
	public @ResponseBody YtmChannelsScan getAChannel(){
		channelLock.lock();
		try{
			return channelService.findAnAvailableChannel();
		}finally{
			channelLock.unlock();
		}
	}
	
	@RequestMapping(value = "channelById/{channelId}", method = RequestMethod.GET)
	public @ResponseBody YtmChannelsScan getAChannelById(@PathVariable("channelId") String channelId){
		return channelService.findById(channelId);
	}
	
	@RequestMapping(value = "channelcompleted", method = RequestMethod.POST)
	public @ResponseBody ServiceStatus channeCompleted(@RequestParam("channelId") String channelId){
		channelService.completeChannel(channelId);
		return ServiceStatus.SUCCESS;
	}
	
	@RequestMapping(value = "updatechannel", method = RequestMethod.POST)
	public @ResponseBody ServiceStatus updateChannel(@RequestBody YtmChannelsScan channel){
		channelService.saveChannel(channel);
		return ServiceStatus.SUCCESS;
	}
	
	@ExceptionHandler(Throwable.class)
	public @ResponseBody ServiceStatus handleException(Throwable t){
		LOG.error("Failed http request", t);
		return ServiceStatus.FAILURE;
	}
}
