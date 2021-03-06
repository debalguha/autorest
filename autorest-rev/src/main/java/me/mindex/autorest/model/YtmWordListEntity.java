package me.mindex.autorest.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ytm_word_list")
@Indexed
public class YtmWordListEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ywlId;
	private String word;
	@Field(store = Store.YES, analyze = Analyze.NO)
	private Boolean isComplete;
	@Field(store = Store.YES, analyze = Analyze.NO)
	private Boolean isWorking;
	@Temporal(TemporalType.TIMESTAMP)
	@Field(store = Store.YES, analyze = Analyze.NO)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date lastDate;
	
	@Transient
	private Long lastTimeStamp;

	public Long getLastTimeStamp() {
		return lastTimeStamp;
	}

	public YtmWordListEntity() {
	}

	public YtmWordListEntity(String word, boolean isComplete, boolean isWorking) {
		this.word = word;
		this.isComplete = isComplete;
		this.isWorking = isWorking;
		setLastDate(new Date());
	}

	public YtmWordListEntity(String word, boolean isComplete, boolean isWorking, Date lastDate) {
		this.word = word;
		this.isComplete = isComplete;
		this.isWorking = isWorking;
		setLastDate(lastDate);
	}
	
	public YtmWordListEntity(Long id, String word, boolean isComplete, boolean isWorking, Date lastDate) {
		this.ywlId = id;
		this.word = word;
		this.isComplete = isComplete;
		this.isWorking = isWorking;
		setLastDate(lastDate);
	}

	public Long getYwlId() {
		return this.ywlId;
	}

	public void setYwlId(Long ywlId) {
		this.ywlId = ywlId;
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public boolean isIsComplete() {
		return this.isComplete;
	}

	public void setIsComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public boolean isIsWorking() {
		return this.isWorking;
	}

	public void setIsWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}

	public Date getLastDate() {
		return this.lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
		this.lastTimeStamp = lastDate==null?0:lastDate.getTime();
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public boolean isWorking() {
		return isWorking;
	}

	public void setWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
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
		YtmWordListEntity other = (YtmWordListEntity) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "YtmWordListEntity [ywlId=" + ywlId + ", word=" + word + ", isComplete=" + isComplete + ", isWorking="
				+ isWorking + ", lastDate=" + lastDate + ", lastTimeStamp=" + lastTimeStamp + "]";
	}

}
