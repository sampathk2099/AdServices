package com.adservice.campaign.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.adservice.campaign.domain.PartnerAd;
import com.adservice.campaign.service.PartnerAdService;
import com.adservice.campaign.web.PartnerAdController;
import com.fasterxml.jackson.core.type.TypeReference;


@RunWith(SpringRunner.class)
@WebMvcTest(PartnerAdController.class)
public class PartnerAdControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	PartnerAdService partnerAdService;
	
	private static final String ROOT_URL = "/";
	private static final String URL = "/ad";

	@Test
	public void testCreatePartnerAd() throws Exception {
		
		PartnerAd partnerAdStub = new PartnerAd("Braun", 60, LocalDateTime.now(), "Series 7 - Smart Shaver");
		when(partnerAdService.save(any(PartnerAd.class))).thenReturn(partnerAdStub);
		
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(TestUtils.objectToJson(partnerAdStub)))
				.andReturn();
		
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.CREATED.value(), status);
		
		verify(partnerAdService).save(any(PartnerAd.class));
		
		PartnerAd partnerAdResult = TestUtils.jsonToObject(result.getResponse().getContentAsString(), PartnerAd.class);
		assertNotNull(partnerAdResult);
		assertEquals("Braun", partnerAdResult.getPartnerId());
		
		
	}
	
	
	@Test
	public void testGetPartnerAd() throws Exception {
		PartnerAd partnerAdStub = new PartnerAd("Braun", 60, LocalDateTime.now(), "Series 7 - Smart Shaver");
		when(partnerAdService.getPartnerAdById(any(String.class))).thenReturn(partnerAdStub);

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(URL + "/{id}", new String("Braun"))
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		verify(partnerAdService).getPartnerAdById(any(String.class));

		PartnerAd partnerAdResult = TestUtils.jsonToObject(result.getResponse().getContentAsString(), PartnerAd.class);
		assertNotNull(partnerAdResult);
		assertEquals("Braun", partnerAdResult.getPartnerId());
	}
	
	
	@Test
	public void testGetPartnerAdNotExist() throws Exception {

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(URL + "/{id}", new String("abc"))
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND.value(), status);

		verify(partnerAdService).getPartnerAdById(any(String.class));

		PartnerAd partnerAdResult = TestUtils.jsonToObject(result.getResponse().getContentAsString(), PartnerAd.class);
		assertNull(partnerAdResult);
	}
	
	@Test
	public void testGetAllPartnerAd() throws Exception {

		List<PartnerAd> partnerAdList = createDummyPartnerAds();
		when(partnerAdService.getAllPartnerAd()).thenReturn(partnerAdList);

				MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(ROOT_URL)
					.accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		verify(partnerAdService).getAllPartnerAd();

		TypeReference<List<PartnerAd>> typeRef = new TypeReference<List<PartnerAd>>() {
		};
		@SuppressWarnings("unchecked")
		List<PartnerAd> partnerAdListResult = TestUtils.jsonToList(result.getResponse().getContentAsString(), typeRef);

		assertNotNull("PartnerAd not found", partnerAdListResult);
		assertEquals("PartnerAd list size is not matching", partnerAdList.size(), partnerAdListResult.size());

	}
	
	
	
	private List<PartnerAd> createDummyPartnerAds() {
		PartnerAd partnerAd1 = new PartnerAd("Kirkland", 120, LocalDateTime.now(), "UltraClean - Odor Eliminating Technology");
		PartnerAd partnerAd2 = new PartnerAd("P&G", 190, LocalDateTime.now(), "Crest ProHealth - 99% Germ Killer");
		PartnerAd partnerAd3 = new PartnerAd("Amazon", 80, LocalDateTime.now(), "Simple Storage Service - deliver 99.999999999% durability");
		List<PartnerAd> partnerAdList = Arrays.asList(partnerAd1, partnerAd2, partnerAd3);
		return partnerAdList;
	}
	

}
