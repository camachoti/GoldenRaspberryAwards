package com.example.goldenraspberryawards;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
class GoldenRaspberryAwardsApplicationTests {

	@Autowired
	public MockMvc mockMvc;

	@Test
	void intervalControllerTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.get(new URI("/"))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string("{\"min\":[{\"producer\":\"Joel Silver\",\"interval\":1,\"previousWin\":1990,\"followingWin\":1991}],\"max\":[{\"producer\":\"Matthew Vaughn\",\"interval\":13,\"previousWin\":2002,\"followingWin\":2015}]}"));
	}

	@Test
	void delete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.delete(new URI("/"))
						.param("id", "1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	void post() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.post(new URI("/"))
						.content("{\n" +
								"    \"title\": \"Duna\",\n" +
								"    \"year\": 2021,\n" +
								"    \"producer\": \"Denis Villeneuve\",\n" +
								"    \"studio\": \"Warner\",\n" +
								"    \"teste\": true\n" +
								"}")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	void put() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.put(new URI("/"))
						.content("{\n" +
								"    \"id\": 4,\n" +
								"    \"title\": \"Duna\",\n" +
								"    \"year\": 2021,\n" +
								"    \"producer\": \"Denis Villeneuve\",\n" +
								"    \"studio\": \"Warner\",\n" +
								"    \"teste\": true\n" +
								"}")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

}
