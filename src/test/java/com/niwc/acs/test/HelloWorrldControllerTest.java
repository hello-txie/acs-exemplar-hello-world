package com.niwc.acs.test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niwc.acs.model.Coord;
import com.niwc.acs.service.HelloWorldService;

@WebMvcTest
@AutoConfigureMockMvc
public class HelloWorrldControllerTest {

	@MockBean
	private HelloWorldService service;
	
	@Autowired
	private MockMvc mockMvc; 
	
    @BeforeEach
    public void setUp() {
    	Coord coordOne = new Coord(1.0, 2.0);
    	Coord coordTwo = new Coord(3.0, 4.0);
    	Coord coordThree = new Coord(5.0, 6.0);
    	
    	List<Coord> coords = new ArrayList<Coord>(); 
    	coords.add(coordOne);
    	coords.add(coordTwo);
    	coords.add(coordThree); 

        Mockito.when(service.getAllCoords()).thenReturn(coords);
        Mockito.when(service.createOrSaveCoord(any())).thenReturn(coordOne);
    }
	
	@Test
	public void testGetAllCoord() throws Exception {
		mockMvc.perform(get("/coords/all"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$[0].id", is(0)))
			.andExpect(jsonPath("$[0].lat", is(1.0)))
			.andExpect(jsonPath("$[0].lng", is(2.0)))
			.andExpect(jsonPath("$[1].id", is(0)))
			.andExpect(jsonPath("$[1].lat", is(3.0)))
			.andExpect(jsonPath("$[1].lng", is(4.0)))
			.andExpect(jsonPath("$[2].id", is(0)))
			.andExpect(jsonPath("$[2].lat", is(5.0)))
			.andExpect(jsonPath("$[2].lng", is(6.0)));
	}
	
	@Test
	public void testSaveCoordWithSuccess() throws Exception {
		Coord coordOne = new Coord(1.0, 2.0);
		ObjectMapper mapper = new ObjectMapper();
		String jsonBody = mapper.writeValueAsString(coordOne);
		
		mockMvc.perform(post("/coords")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(jsonBody))
			.andExpect(status().isOk());
	}
	
	@Test
	public void testSaveCoordWithBadRequest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String jsonBody = mapper.writeValueAsString(null);
		
		mockMvc.perform(post("/coords")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(jsonBody))
			.andExpect(status().isBadRequest());
	}
}
