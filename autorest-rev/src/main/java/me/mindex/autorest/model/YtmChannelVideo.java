package me.mindex.autorest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ytm_channel_videos")
public class YtmChannelVideo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "YCVID")
	private Long id;
	@Column(name = "ProviderID")
    private short providerId;
	@Column(name = "VideoJSON")
    private String videoJson;
	@Column(name = "EntryDate")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date entryDate;
	@Column(name = "VideoID")
    private String videoId;
	@Column(name = "SearchTerm")
    private String searchTerm;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ChannelID", nullable = false)
    private YtmChannelsScan channel;
    
	public YtmChannelVideo() {
		super();
	}

	public YtmChannelVideo(short providerId, String videoJson, Date entryDate, String videoId, String searchTerm,
			YtmChannelsScan channel) {
		super();
		this.providerId = providerId;
		this.videoJson = videoJson;
		this.entryDate = entryDate;
		this.videoId = videoId;
		this.searchTerm = searchTerm;
		this.channel = channel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public short getProviderId() {
		return providerId;
	}

	public void setProviderId(short providerId) {
		this.providerId = providerId;
	}

	public String getVideoJson() {
		return videoJson;
	}

	public void setVideoJson(String videoJson) {
		this.videoJson = videoJson;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public YtmChannelsScan getChannel() {
		return channel;
	}

	public void setChannel(YtmChannelsScan channel) {
		this.channel = channel;
	}

}
