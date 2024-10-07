package com.example.goldenraspberryawards;

import com.example.goldenraspberryawards.model.Movie;
import com.example.goldenraspberryawards.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
		"spring.datasource.url=jdbc:h2:~/test2",
		"spring.jpa.defer-datasource-initialization=false"
})
class GoldenRaspberryAwardsApplicationTests {

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	public MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		List<Movie> movies = new ArrayList<>();
		movies.add(new Movie(2024, "Duna 2", "Warner", "Denis Villeneuve", true));
		movies.add(new Movie(2021, "Duna 1", "Warner", "Denis Villeneuve", true));
		movies.add(new Movie(2012, "Django Unchained", "Sony Pictures", "Quentin Tarantino", true));
		movies.add(new Movie(1994, "Pulp Fiction", "Miramax Films", "Quentin Tarantino", true));

		movieRepository.saveAllAndFlush(movies);
	}

	@Test
	void intervalControllerTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.get(new URI("/"))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string("{\"min\":[{\"producer\":\"Denis Villeneuve\",\"interval\":3,\"previousWin\":2021,\"followingWin\":2024}],\"max\":[{\"producer\":\"Quentin Tarantino\",\"interval\":18,\"previousWin\":1994,\"followingWin\":2012}]}"));
	}

}
