package com.adservice.campaign.exception;

public class AdCampaignNotFoundException extends RuntimeException {
	
private static final long serialVersionUID = 1L;
	
	private String id;

	public AdCampaignNotFoundException(String id) {
		super();
		this.id = id;
	}
	
	public String getId() {
        return id;
    }

}
