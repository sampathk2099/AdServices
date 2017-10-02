package com.adservice.campaign.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adservice.campaign.domain.PartnerAd;
import com.adservice.campaign.exception.AdCampaignAlreadyExistException;
import com.adservice.campaign.repositories.PartnerAdRepository;
import com.adservice.campaign.util.DateTimeUtility;

@Service
public class PartnerAdServiceImpl implements PartnerAdService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PartnerAdServiceImpl.class);
	
	private PartnerAdRepository partnerAdRepository;
	
	@Autowired
	public PartnerAdServiceImpl(PartnerAdRepository partnerAdRepository) {
		this.partnerAdRepository = partnerAdRepository;
	}
	
	@Override
	public PartnerAd save(PartnerAd partnerAd) {
		LOGGER.info("+++++++++ PartnerAdServiceImpl | save(PartnerAd partnerAd) +++++++++++++");
		
		if(isPartnerCampaignActive(partnerAd.getPartnerId())) {
			throw new AdCampaignAlreadyExistException(partnerAd.getPartnerId());
		}
		return partnerAdRepository.save(partnerAd);
	}	
	
	@Override
	public List<PartnerAd> getAllPartnerAd() {
		LOGGER.info("+++++++++ PartnerAdServiceImpl | getAllPartnerAd() +++++++++++++"); 
		return (List<PartnerAd>) partnerAdRepository.findAll();
	}
	
	@Override
	public PartnerAd getPartnerAdById(String partnerId) {
		LOGGER.info("+++++++++ PartnerAdServiceImpl | getPartnerAdById(String partnerId) +++++++++++++");
		if(!isPartnerCampaignActive(partnerId)) {
			return null;
		}
		return partnerAdRepository.findOne(partnerId);
	}
	
	@Override
	public PartnerAd update(String partnerAdId, PartnerAd partnerAd) {
		PartnerAd existingPartnerAd = partnerAdRepository.findOne(partnerAdId);
		if (existingPartnerAd == null) {
			return null;
			//throw new AdCampaignNotFoundException(partnerAdId);
		}
		return partnerAdRepository.save(partnerAd);
	}
	
	@Override
	public boolean delete(String partnerAdId) {
		LOGGER.info("+++++++++ PartnerAdServiceImpl | delete(String id) +++++++++++++");		
		PartnerAd partnerAd = partnerAdRepository.findOne(partnerAdId);
		if (partnerAd == null) {
			return false;
		}		
		partnerAdRepository.delete(partnerAdId);
		return true;
	}
	
	private boolean isPartnerCampaignActive(String partnerAdId) {
		boolean isCampaignActive = false;
		PartnerAd partnerAd = partnerAdRepository.findOne(partnerAdId);

		if (partnerAd != null) {
			LocalDateTime campaingDuration = DateTimeUtility.addSecondsToCreatedDateTime(partnerAd.getCreationDate(),
					partnerAd.getDuration());

			LocalDateTime currentTime = LocalDateTime.now();
			if (DateTimeUtility.isCurrentTimeIsLessThanCampignTime(currentTime, campaingDuration)) {
				isCampaignActive = true;
			}
		}
		return isCampaignActive;
	}


}
