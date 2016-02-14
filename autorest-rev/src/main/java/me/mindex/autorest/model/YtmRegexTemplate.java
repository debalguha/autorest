package me.mindex.autorest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ytm_regextamplate")
public class YtmRegexTemplate {

	@Id
	@Column(name = "RegexID")
	private Integer regexId;
	@Column(name = "RegexString")
	private String regexString;
	@Column(name = "RegexTitle")
	private String regexTitle;

	public YtmRegexTemplate() {
	}

	public YtmRegexTemplate(String regexString, String regexTitle) {
		this.regexString = regexString;
		this.regexTitle = regexTitle;
	}

	public Integer getRegexId() {
		return this.regexId;
	}

	public void setRegexId(Integer regexId) {
		this.regexId = regexId;
	}

	public String getRegexString() {
		return this.regexString;
	}

	public void setRegexString(String regexString) {
		this.regexString = regexString;
	}

	public String getRegexTitle() {
		return this.regexTitle;
	}

	public void setRegexTitle(String regexTitle) {
		this.regexTitle = regexTitle;
	}

}
