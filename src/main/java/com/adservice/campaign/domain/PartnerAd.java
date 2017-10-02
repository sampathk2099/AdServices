package com.adservice.campaign.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


import com.adservice.campaign.util.LocalDateTimeDeserializer;
import com.adservice.campaign.util.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class PartnerAd implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "partner_id")
	private String partnerId;
	
	private int duration; //in seconds
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name = "creation_date")
	private LocalDateTime creationDate;
	
	@Column(name = "ad_content")
	private String adContent;
	
	public PartnerAd() {
		super();
	}

	public PartnerAd(String partnerId, int duration, LocalDateTime creationDate, String adContent) {
		super();
		this.partnerId = partnerId;
		this.duration = duration;
		this.creationDate = creationDate;
		this.adContent = adContent;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getAdContent() {
		return adContent;
	}

	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PartnerAd [partnerId=" + partnerId + ", duration=" + duration + ", creationDate=" + creationDate
				+ ", adContent=" + adContent + "]";
	}
	
	
	
}
