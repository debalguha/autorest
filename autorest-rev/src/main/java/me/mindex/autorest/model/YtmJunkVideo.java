package me.mindex.autorest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ytm_junkvideo")
public class YtmJunkVideo {
	@Id
	@Column(name = "JunkID")
	private int id;
	@Column(name = "VideoID")
	private String videoId;
	@Column(name = "VideoUploadedBy")
	private String uploadedBy;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getUploadedBy() {
		return uploadedBy;
	}
	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		YtmJunkVideo other = (YtmJunkVideo) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "YtmJunkVideo [id=" + id + ", videoId=" + videoId + ", uploadedBy=" + uploadedBy + "]";
	}
	
}
