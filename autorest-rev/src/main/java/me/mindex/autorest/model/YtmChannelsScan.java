package me.mindex.autorest.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ytm_channels_scan")
public class YtmChannelsScan  implements Comparable<YtmChannelsScan>{

	@Id
	private String channelId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date entryDate;
	private String scanHistory;
	private boolean scanComplete;
	private short providerId;
	private boolean isWorking;
	private String channelTitle;
	private Integer videosCount;
	private String googleId;
	private Integer totalVideos;
	private Integer commentCount;
	private Long viewCount;
	private Integer subscriberCount;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<YtmChannelVideo> videos;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<TblVideos> tblVideos;

	public YtmChannelsScan() {
	}

	public YtmChannelsScan(String channelId, Date entryDate, boolean scanComplete, short providerId, boolean isWorking) {
		this.channelId = channelId;
		this.entryDate = entryDate;
		this.scanComplete = scanComplete;
		this.providerId = providerId;
		this.isWorking = isWorking;
	}

	public YtmChannelsScan(String channelId, Date entryDate, String scanHistory, boolean scanComplete, short providerId, boolean isWorking, String channelTitle, Integer videosCount, String googleId, Integer totalVideos, Integer commentCount, Long viewCount, Integer subscriberCount) {
		this.channelId = channelId;
		this.entryDate = entryDate;
		this.scanHistory = scanHistory;
		this.scanComplete = scanComplete;
		this.providerId = providerId;
		this.isWorking = isWorking;
		this.channelTitle = channelTitle;
		this.videosCount = videosCount;
		this.googleId = googleId;
		this.totalVideos = totalVideos;
		this.commentCount = commentCount;
		this.viewCount = viewCount;
		this.subscriberCount = subscriberCount;
	}

	public String getChannelId() {
		return this.channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Date getEntryDate() {
		return this.entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getScanHistory() {
		return this.scanHistory;
	}

	public void setScanHistory(String scanHistory) {
		this.scanHistory = scanHistory;
	}

	public boolean isScanComplete() {
		return this.scanComplete;
	}

	public void setScanComplete(boolean scanComplete) {
		this.scanComplete = scanComplete;
	}

	public short getProviderId() {
		return this.providerId;
	}

	public void setProviderId(short providerId) {
		this.providerId = providerId;
	}

	public boolean isIsWorking() {
		return this.isWorking;
	}

	public void setIsWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}

	public String getChannelTitle() {
		return this.channelTitle;
	}

	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}

	public Integer getVideosCount() {
		return this.videosCount;
	}

	public void setVideosCount(Integer videosCount) {
		this.videosCount = videosCount;
	}

	public String getGoogleId() {
		return this.googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public Integer getTotalVideos() {
		return this.totalVideos;
	}

	public void setTotalVideos(Integer totalVideos) {
		this.totalVideos = totalVideos;
	}

	public Integer getCommentCount() {
		return this.commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Long getViewCount() {
		return this.viewCount;
	}

	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

	public Integer getSubscriberCount() {
		return this.subscriberCount;
	}

	public void setSubscriberCount(Integer subscriberCount) {
		this.subscriberCount = subscriberCount;
	}

	public Set<YtmChannelVideo> getVideos() {
		return videos;
	}

	public void setVideos(Set<YtmChannelVideo> videos) {
		this.videos = videos;
	}

	public Set<TblVideos> getTblVideos() {
		return tblVideos;
	}

	public void setTblVideos(Set<TblVideos> tblVideos) {
		this.tblVideos = tblVideos;
	}

	@Override
	public int compareTo(YtmChannelsScan other) {
		return this.videosCount>other.videosCount?1:this.videosCount == other.videosCount?0:-1;
	}

}
