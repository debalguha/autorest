package me.mindex.autorest.service;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import me.mindex.autorest.model.TblVideos;
import me.mindex.autorest.model.YtmChannelVideo;
import me.mindex.autorest.model.YtmChannelsScan;
import me.mindex.autorest.persistence.repo.TblVideoRepo;
import me.mindex.autorest.persistence.repo.YtmChannelVideoRepo;
import me.mindex.autorest.persistence.repo.YtmChannelsScanRepo;
import me.mindex.autorest.persistence.repo.YtmWordListRepo;
import me.mindex.autorest.service.predicate.PredicateSpecFactory;
import me.mindex.autorest.web.CountersInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Service("videoService")
@Qualifier("videoService")
public class VideoService {
	@Autowired
	private YtmChannelVideoRepo videoRepo;
	@Autowired
	private TblVideoRepo tblVideorepo;
	@Autowired
	private YtmChannelsScanRepo channelRepo;
	@Autowired
	private YtmWordListRepo wordListRepo;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public YtmChannelVideo saveVideo(YtmChannelVideo video) {
		return videoRepo.save(video);
	}

	@Transactional(readOnly = true)
	public Collection<String> getAllVideoCodes(String channelId) {
		YtmChannelsScan channelsScan = channelRepo.findOne(channelId);
		Set<String> videoCodes = Sets.newHashSet(channelsScan.getVideos().stream().map(YtmChannelVideo::getVideoId).collect(Collectors.toSet()));
		videoCodes.addAll(channelsScan.getTblVideos().stream().map(TblVideos::getVideoCode).collect(Collectors.toSet()));
		return videoCodes;
	}
	
	@Transactional(readOnly = true)
	public Collection<String> getAllVideoCodes() {
		Set<String> videoCodes = Sets.newHashSet(videoRepo.findAll().stream().map(YtmChannelVideo::getVideoId).collect(Collectors.toSet()));
		videoCodes.addAll(tblVideorepo.findAll().stream().map(TblVideos::getVideoCode).collect(Collectors.toSet()));
		return videoCodes;
	}
	
	@Transactional(readOnly = true)
	public List<Map<?, ?>> getYearLyVideoStatistics(){
		List<Map<?, ?>> retList = Lists.newArrayList();
		retList.add(tblVideorepo.yearlyVideoCount());
		return retList;
	}
	
	@Transactional(readOnly = true)
	public List<Map<?, ?>> getCategoricalVideoUploads(){
		List<Map<?, ?>> retList = Lists.newArrayList();
		retList.addAll(tblVideorepo.categoricalVideoUploadDates());
		return retList;
	}
	
	@Transactional(readOnly = true)
	public Collection<YtmChannelsScan> getChannelsWithVideoCountLE(int videoCount){
		return channelRepo.findAll(PredicateSpecFactory.buildLEVideoCountChannelSpec(videoCount), new PageRequest(1, 100)).getContent();
	}
	
	@Transactional(readOnly = true)
	public CountersInfo getAppStatistics(){
		CountersInfo counterInfo = new CountersInfo();
		counterInfo.setChannelsCount(channelRepo.count());
		counterInfo.setScanedChannels(Long.valueOf(channelRepo.scanCompleteStats(Boolean.TRUE).size()));
		counterInfo.setWordsCount(Long.valueOf(wordListRepo.scanCompleteStats(Boolean.FALSE).size()));
		counterInfo.setWordsCount(wordListRepo.count());
		counterInfo.setVideosCount(videoRepo.count());
		return counterInfo;
	}
	
	@Transactional(readOnly = true)
	public Collection<YtmChannelsScan> getChannelsWithVideoCountGT(int videoCount){
		return channelRepo.channelWithVideoCountGT(videoCount);
	}

	@Transactional(readOnly = true)
	public Collection<YtmChannelVideo> getVideosForChannel(String channelId, int start) {
		return videoRepo.findAll(PredicateSpecFactory.buildVideoFromChannelSpec(channelRepo.findOne(String.valueOf(channelId))), new PageRequest((start/100)+1, 100)).getContent();
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deleteChannelVideos(String channelId) {
		YtmChannelsScan channel = channelRepo.findOne(channelId);
		videoRepo.deleteInBatch(channel.getVideos());
	}
}
