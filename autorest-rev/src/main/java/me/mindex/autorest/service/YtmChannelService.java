package me.mindex.autorest.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import me.mindex.autorest.model.YtmChannelsScan;
import me.mindex.autorest.persistence.repo.TblVideoRepo;
import me.mindex.autorest.persistence.repo.YtmChannelVideoRepo;
import me.mindex.autorest.persistence.repo.YtmChannelsScanRepo;
import me.mindex.autorest.service.predicate.PredicateSpecFactory;

@Service("channelService")
@Qualifier("channelService")
@Transactional
public class YtmChannelService {
	@Autowired
	private YtmChannelsScanRepo channelRepo;
	@Autowired
	private TblVideoRepo tblVideoRepo;
	@Autowired
	private YtmChannelVideoRepo channelVideoRepo;
	@Transactional(readOnly = true)
	public Collection<String> getAllChannelIds() {
		return channelRepo.findAll().stream().map(YtmChannelsScan::getChannelId).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public YtmChannelsScan findById(String channelId){
		return channelRepo.findOne(channelId);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public YtmChannelsScan saveChannel(YtmChannelsScan channel) {
		return channelRepo.save(channel);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public YtmChannelsScan findAnAvailableChannel() {
		YtmChannelsScan channel = channelRepo.findAll(PredicateSpecFactory.buildAvailableChannelSpec(), new PageRequest(1, 1)).getContent().iterator().next();
		channel.setIsWorking(true);
		channel.setScanComplete(false);
		saveChannel(channel);
		return channel;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public YtmChannelsScan completeChannel(String channelId) {
		YtmChannelsScan channelsScan = channelRepo.findOne(channelId);
		channelsScan.setScanComplete(true);
		channelsScan.setIsWorking(false);
		channelsScan.setVideosCount(channelVideoRepo.videoCountForChannel(channelsScan)+tblVideoRepo.videoCountForChannel(channelsScan));
		return saveChannel(channelsScan);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean updateLongWorkingChannelsToAvailable(Date leastAllowedTime){
		List<YtmChannelsScan> longRunning = channelRepo.findAll(PredicateSpecFactory.buildLongWorkingChannelPredicateSpec(leastAllowedTime));
		longRunning.forEach(c -> c.setIsWorking(false));
		channelRepo.save(longRunning);
		return true;
	}
	
	
}
