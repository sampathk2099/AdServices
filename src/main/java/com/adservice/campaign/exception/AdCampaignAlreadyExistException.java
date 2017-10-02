package com.adservice.campaign.exception;

public class AdCampaignAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String id;

	public AdCampaignAlreadyExistException(String id) {
		super();
		this.id = id;
	}
	
	public String getId() {
        return id;
    }
	
}
