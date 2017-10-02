package com.adservice.campaign.web;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

	@SuppressWarnings("rawtypes")
	public static List jsonToList(String json, TypeReference mapType)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		List jsonList = objectMapper.readValue(json, mapType);
		return jsonList;
	}

	public static String objectToJson(Object obj) {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonStr = "";
		try {
			jsonStr = objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	@SuppressWarnings("unchecked")
	public static <T> T jsonToObject(String json, Class<T> classOf) {
		ObjectMapper objectMapper = new ObjectMapper();
		Object obj = null;
		try {
			obj = objectMapper.readValue(json, classOf);
		} catch (JsonParseException e) {
			//e.printStackTrace();
		} catch (JsonMappingException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		}
		return (T) obj;
	}
	
	
}
