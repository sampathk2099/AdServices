package com.adservice.campaign.web;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adservice.campaign.domain.PartnerAd;
import com.adservice.campaign.exception.ActiveAdCampaignNotFoundException;
import com.adservice.campaign.exception.AdCampaignEmptyListException;
import com.adservice.campaign.exception.AdCampaignNotFoundException;
import com.adservice.campaign.service.PartnerAdService;


@RestController
public class PartnerAdController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PartnerAdController.class);
	private PartnerAdService partnerAdService;
	
	@Autowired
	public PartnerAdController(PartnerAdService partnerAdService) {
		this.partnerAdService = partnerAdService;
	}
	
	// REST Service to CREATE Ad Campaign
	 
	@RequestMapping(value = "/ad", method = RequestMethod.POST)
	public ResponseEntity<PartnerAd> insertPartnerAd(@RequestBody PartnerAd partnerAd) {
		LOGGER.info("/ad/" + "api POST request called...");
		LocalDateTime adCreationDateTime = LocalDateTime.now();
		partnerAd.setCreationDate(adCreationDateTime);
		PartnerAd createdPartnerAd = partnerAdService.save(partnerAd);
		LOGGER.info("Added Ad Campaign:: " + createdPartnerAd);
		return new ResponseEntity<PartnerAd>(createdPartnerAd, HttpStatus.CREATED);
	}
	
	
	//  REST Service to GET all Ad Campaign
	 
	@RequestMapping("/")
	public ResponseEntity<List<PartnerAd>> getAllPartnerAd() {
		LOGGER.info("root /  api GET request called...");
		List<PartnerAd> partners = (List<PartnerAd>) partnerAdService.getAllPartnerAd();
		if (partners.isEmpty()) {
			LOGGER.info("No Ad Campaign exists");
			throw new AdCampaignEmptyListException();
		}
		LOGGER.info("Found " + partners.size() + " Ad Campaign");
		LOGGER.debug(partners.toString());
		LOGGER.debug(Arrays.toString(partners.toArray()));
		
		return new ResponseEntity<List<PartnerAd>>(partners, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ad/{partnerAdId}", method = RequestMethod.GET)
	public ResponseEntity<PartnerAd> getPartnerAd(@PathVariable(value="partnerAdId") final String partnerAdId) {
		LOGGER.info("/ad/" + partnerAdId + " api GET request called...");
		
		PartnerAd partnerAd = partnerAdService.getPartnerAdById(partnerAdId);
		if(partnerAd == null) {
			throw new ActiveAdCampaignNotFoundException(partnerAdId);
		}
		return new ResponseEntity<PartnerAd>(partnerAd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ad/{partnerAdId}", method = RequestMethod.PUT)
	public ResponseEntity<PartnerAd> updatePartnerAd(@PathVariable(value="partnerAdId") String partnerAdId, @RequestBody PartnerAd partnerAd) {
		LOGGER.info("/ad/" + partnerAdId + " api UPDATE request called...");
		PartnerAd updatedPartnerAd = partnerAdService.update(partnerAdId, partnerAd);
		if (updatedPartnerAd == null) {
			LOGGER.debug("Ad Campaing with id " + partnerAdId + " does not exists");
			throw new AdCampaignNotFoundException(partnerAdId);
		} 
		LOGGER.info("Ad Campaign updated with this value: {}", updatedPartnerAd);
		return new ResponseEntity<PartnerAd>(updatedPartnerAd, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/ad/{partnerAdId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePartnerAd(@PathVariable(value="partnerAdId") String partnerAdId) {
		LOGGER.info("/ad/" + partnerAdId + " api DELETE request called...");
		if(partnerAdService.delete(partnerAdId)) {
			LOGGER.info("Ad Campaign with id " + partnerAdId + " deleted...");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		} 
		throw new AdCampaignNotFoundException(partnerAdId);
	}
	
}
