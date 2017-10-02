package com.adservice.campaign.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
	
	private static final String ERROR_CODE = "errorCode";
	private static final String ID = "id";
	private static final String REASON = "reason";
	private static final String URL = "url";
	
	@ExceptionHandler(value = { ActiveAdCampaignNotFoundException.class })
    protected ResponseEntity<Object> handleActiveAdCampaignNotFoundException(HttpServletRequest req, ActiveAdCampaignNotFoundException ex) {
       LOGGER.error(ex.getMessage(), ex);
       Map<String, String> map = new HashMap<String, String>();
       map.put(ERROR_CODE, String.valueOf(HttpStatus.NOT_FOUND.value()));
       map.put(ID, String.valueOf(ex.getId()));
       map.put(REASON, "No active Ad Campaign exist for the specified partner!!!");
       map.put(URL, req.getRequestURI());

       BodyBuilder responseBudiler = ResponseEntity.status(HttpStatus.NOT_FOUND);
       ResponseEntity<Object> response = responseBudiler.body(map);
       return response;
     }
	
	@ExceptionHandler(value = { AdCampaignNotFoundException.class })
    protected ResponseEntity<Object> handleAdCampaignNotFoundException(HttpServletRequest req, AdCampaignNotFoundException ex) {
       LOGGER.error(ex.getMessage(), ex);
       Map<String, String> map = new HashMap<String, String>();
       map.put(ERROR_CODE, String.valueOf(HttpStatus.NOT_FOUND.value()));
       map.put(ID, String.valueOf(ex.getId()));
       map.put(REASON, "No Ad Campaign found for the specified partner!!!");
       map.put(URL, req.getRequestURI());

       BodyBuilder responseBudiler = ResponseEntity.status(HttpStatus.NOT_FOUND);
       ResponseEntity<Object> response = responseBudiler.body(map);
       return response;
     }
	
	@ExceptionHandler(value = { AdCampaignAlreadyExistException.class })
    protected ResponseEntity<Object> handleAdCampaignAlreadyExistException(HttpServletRequest req, AdCampaignAlreadyExistException ex) {
       LOGGER.error(ex.getMessage(), ex);
       Map<String, String> map = new HashMap<String, String>();
       map.put(ERROR_CODE, String.valueOf(HttpStatus.CONFLICT.value()));
       map.put(ID, String.valueOf(ex.getId()));
       map.put(REASON, "Only one active campaign can exist for a given partner!!!");
       map.put(URL, req.getRequestURI());

       BodyBuilder responseBudiler = ResponseEntity.status(HttpStatus.CONFLICT);
       ResponseEntity<Object> response = responseBudiler.body(map);
       return response;
     }
	
	@ExceptionHandler(value = { AdCampaignEmptyListException.class })
    protected ResponseEntity<Object> handleAdCampaignEmptyListException(HttpServletRequest req, AdCampaignEmptyListException ex) {
       LOGGER.error(ex.getMessage(), ex);
       Map<String, String> map = new HashMap<String, String>();
       map.put(ERROR_CODE, String.valueOf(HttpStatus.NO_CONTENT.value()));
       map.put(REASON, "No Ad Campaign exist!!!");
       map.put(URL, req.getRequestURI());

       BodyBuilder responseBudiler = ResponseEntity.status(HttpStatus.CONFLICT);
       ResponseEntity<Object> response = responseBudiler.body(map);
       return response;
     }
	

}
