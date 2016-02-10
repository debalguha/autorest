package me.mindex.autorest.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ytm_word_list")
public class YtmWordListEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ywlId;
	private String word;
	private Boolean isComplete;
	private Boolean isWorking;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastDate;

	public YtmWordListEntity() {
	}

	public YtmWordListEntity(String word, boolean isComplete, boolean isWorking) {
		this.word = word;
		this.isComplete = isComplete;
		this.isWorking = isWorking;
		this.lastDate = new Date();
	}

	public YtmWordListEntity(String word, boolean isComplete, boolean isWorking, Date lastDate) {
		this.word = word;
		this.isComplete = isComplete;
		this.isWorking = isWorking;
		this.lastDate = lastDate;
	}

	public Integer getYwlId() {
		return this.ywlId;
	}

	public void setYwlId(Integer ywlId) {
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
		return "YtmWordList [ywlId=" + ywlId + ", word=" + word + ", isComplete=" + isComplete + ", isWorking=" + isWorking + ", lastDate=" + lastDate + "]";
	}

}
