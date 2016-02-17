package me.mindex.autorest.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import me.mindex.autorest.model.YtmChannelsScan;
import me.mindex.autorest.persistence.repo.YtmChannelsScanRepo;
import me.mindex.autorest.service.predicate.PredicateSpecFactory;

@Service("channelService")
@Qualifier("channelService")
@Transactional
public class YtmChannelService {
	@Autowired
	private YtmChannelsScanRepo channelRepo;
	@Transactional(readOnly = true)
	public Collection<String> getAllChannelIds() {
		return channelRepo.findAll().stream().map(YtmChannelsScan::getChannelId).collect(Collectors.toList());
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
		return saveChannel(channelsScan);
	}
	
	
}
