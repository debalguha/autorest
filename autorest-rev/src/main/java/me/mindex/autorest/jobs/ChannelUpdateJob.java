package me.mindex.autorest.jobs;

import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import me.mindex.autorest.service.YtmChannelService;

@Component
public class ChannelUpdateJob {
	@Autowired
	private YtmChannelService channelService;
	
	@Scheduled(fixedRate = 5*60*1000, initialDelay = 3*60*1000)
	public void scanAndUpdateChannelState(){
		Date lastAllowedDate = new DateTime().minusMinutes(21).toDate();
		channelService.updateLongWorkingChannelsToAvailable(lastAllowedDate);
	}
}
