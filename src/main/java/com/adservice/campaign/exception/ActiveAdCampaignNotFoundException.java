package com.adservice.campaign.exception;

public class ActiveAdCampaignNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String id;

	public ActiveAdCampaignNotFoundException(String id) {
		super();
		this.id = id;
	}
	
	public String getId() {
        return id;
    }
}
