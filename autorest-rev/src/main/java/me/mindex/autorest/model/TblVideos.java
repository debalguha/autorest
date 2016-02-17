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
@Table(name = "tbl_video")
public class TblVideos {

    @Column(name="VideoID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long videoId;

    @Column(name="VideoMID")
    private String videoMid;

    @Column(name="VideoSource")
    private Integer videoSource;

    @Column(name="VideoCode")
    private String videoCode;

    @Column(name="VideoTitle")
    private String videoTitle;

    @Column(name="VideoDescription")
    private String videoDescription;

    @Column(name="VideoThumbnail")
    private String videoThumbnail;

    @Column(name="VideoUploadedBy")
    private String videoUploadedBy;

    @Column(name="VideoCategory")
    private String videoCategory;

    @Column(name="VideoViews")
    private Integer videoViews;

    @Column(name="VideoLikes")
    private Integer videoLikes;

    @Column(name="VideoDislikes")
    private Integer videoDislikes;

    @Column(name="VideoLength")
    private Integer videoLength;

    @Column(name="VideoComments")
    private Integer videoComments;

    @Column(name="VideoUploadedDate")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date videoUploadedDate;

    @Column(name="VideoTags")
    private String videoTags;

    @Column(name="SearchTerm")
    private String searchTerm;

    @Column(name="VideoUpdateStatus")
    private Boolean videoUpdateStatus;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "VideoYTChannel")
    private YtmChannelsScan channel;

    public TblVideos() {
    }

    public TblVideos(String videoMid, String videoCode) {
        this.videoMid = videoMid;
        this.videoCode = videoCode;
    }

    public TblVideos(String videoMid, Integer videoSource, String videoCode, String videoTitle, String videoDescription, String videoThumbnail, String videoUploadedBy, String videoCategory, Integer videoViews, Integer videoLikes, Integer videoDislikes, Integer videoLength, Integer videoComments, Date videoUploadedDate, String videoTags, String searchTerm, Boolean videoUpdateStatus) {
        this.videoMid = videoMid;
        this.videoSource = videoSource;
        this.videoCode = videoCode;
        this.videoTitle = videoTitle;
        this.videoDescription = videoDescription;
        this.videoThumbnail = videoThumbnail;
        this.videoUploadedBy = videoUploadedBy;
        this.videoCategory = videoCategory;
        this.videoViews = videoViews;
        this.videoLikes = videoLikes;
        this.videoDislikes = videoDislikes;
        this.videoLength = videoLength;
        this.videoComments = videoComments;
        this.videoUploadedDate = videoUploadedDate;
        this.videoTags = videoTags;
        this.searchTerm = searchTerm;
        this.videoUpdateStatus = videoUpdateStatus;
    }

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public String getVideoMid() {
		return videoMid;
	}

	public void setVideoMid(String videoMid) {
		this.videoMid = videoMid;
	}

	public Integer getVideoSource() {
		return videoSource;
	}

	public void setVideoSource(Integer videoSource) {
		this.videoSource = videoSource;
	}

	public String getVideoCode() {
		return videoCode;
	}

	public void setVideoCode(String videoCode) {
		this.videoCode = videoCode;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public String getVideoDescription() {
		return videoDescription;
	}

	public void setVideoDescription(String videoDescription) {
		this.videoDescription = videoDescription;
	}

	public String getVideoThumbnail() {
		return videoThumbnail;
	}

	public void setVideoThumbnail(String videoThumbnail) {
		this.videoThumbnail = videoThumbnail;
	}

	public String getVideoUploadedBy() {
		return videoUploadedBy;
	}

	public void setVideoUploadedBy(String videoUploadedBy) {
		this.videoUploadedBy = videoUploadedBy;
	}

	public String getVideoCategory() {
		return videoCategory;
	}

	public void setVideoCategory(String videoCategory) {
		this.videoCategory = videoCategory;
	}

	public Integer getVideoViews() {
		return videoViews;
	}

	public void setVideoViews(Integer videoViews) {
		this.videoViews = videoViews;
	}

	public Integer getVideoLikes() {
		return videoLikes;
	}

	public void setVideoLikes(Integer videoLikes) {
		this.videoLikes = videoLikes;
	}

	public Integer getVideoDislikes() {
		return videoDislikes;
	}

	public void setVideoDislikes(Integer videoDislikes) {
		this.videoDislikes = videoDislikes;
	}

	public Integer getVideoLength() {
		return videoLength;
	}

	public void setVideoLength(Integer videoLength) {
		this.videoLength = videoLength;
	}

	public Integer getVideoComments() {
		return videoComments;
	}

	public void setVideoComments(Integer videoComments) {
		this.videoComments = videoComments;
	}

	public Date getVideoUploadedDate() {
		return videoUploadedDate;
	}

	public void setVideoUploadedDate(Date videoUploadedDate) {
		this.videoUploadedDate = videoUploadedDate;
	}

	public String getVideoTags() {
		return videoTags;
	}

	public void setVideoTags(String videoTags) {
		this.videoTags = videoTags;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public Boolean getVideoUpdateStatus() {
		return videoUpdateStatus;
	}

	public void setVideoUpdateStatus(Boolean videoUpdateStatus) {
		this.videoUpdateStatus = videoUpdateStatus;
	}

	public YtmChannelsScan getChannel() {
		return channel;
	}

	public void setChannel(YtmChannelsScan channel) {
		this.channel = channel;
	}


}
