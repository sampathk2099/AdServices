package com.adservice.campaign.service;

import java.util.List;

import com.adservice.campaign.domain.PartnerAd;


public interface PartnerAdService {
	
	public PartnerAd save(PartnerAd partnerAd);
	
	public List<PartnerAd> getAllPartnerAd();
	
	public PartnerAd getPartnerAdById(String partnerAdId);
	
	public PartnerAd update(String partnerAdId, PartnerAd partnerAd);
	
	public boolean delete(String partnerAdId);
	
}
