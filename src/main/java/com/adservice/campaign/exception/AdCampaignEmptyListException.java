package com.adservice.campaign.exception;

public class AdCampaignEmptyListException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public AdCampaignEmptyListException() {
		super();
	}

	public AdCampaignEmptyListException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AdCampaignEmptyListException(String message, Throwable cause) {
		super(message, cause);
	}

	public AdCampaignEmptyListException(String message) {
		super(message);
	}

	public AdCampaignEmptyListException(Throwable cause) {
		super(cause);
	}
	
	
}
