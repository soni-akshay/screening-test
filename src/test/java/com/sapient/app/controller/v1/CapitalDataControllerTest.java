package com.sapient.app.controller.v1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CapitalDataControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CapitalDataController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	public void testForStatus() throws Exception {
		this.mockMvc.perform(get("/api/v1/capitaldata/report")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testForContentType() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/api/v1/capitaldata/report")).andDo(print())
				.andExpect(status().isOk()).andReturn();
		Assert.notNull(result.getResponse().getContentAsString(), "Response should not be null.");
		Assert.hasText(".csv", result.getResponse().getContentAsString());
	}
}
