package com.niwc.acs.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.niwc.acs.impl.HelloWorldServiceImpl;
import com.niwc.acs.model.Coord;
import com.niwc.acs.repository.HelloWorldRepository;
import com.niwc.acs.service.HelloWorldService;

@ExtendWith(SpringExtension.class)
public class HelloWorldServiceTest {

    @TestConfiguration
    static class HelloWorldServiceImplTestContextConfiguration {
 
        @Bean
        public HelloWorldService helloWorldService() {
            return new HelloWorldServiceImpl();
        }
    }
    
    @Autowired
    private HelloWorldService service;
    
    @MockBean
    private HelloWorldRepository repo; 
    
    @Mock
    private Coord coord; 
    
    @BeforeEach
    public void setUp() {
    	Coord coordOne = new Coord(1.0, 2.0);
    	Coord coordTwo = new Coord(3.0, 4.0);
    	Coord coordThree = new Coord(5.0, 6.0);
    	Optional<Coord> coordOptional = Optional.of(new Coord(7.0, 8.0));
    	
    	List<Coord> coords = new ArrayList<Coord>(); 
    	coords.add(coordOne);
    	coords.add(coordTwo);
    	coords.add(coordThree); 
    	
        Mockito.when(repo.findAll())
        .thenReturn(coords);
        
        Mockito.when(repo.save(any()))
        .thenReturn(coordOne);
     
        Mockito.when(repo.findById(1))
        .thenReturn(coordOptional);
        
        Mockito.when(repo.findById(2))
        .thenReturn(Optional.empty());
    }
    
    @Test
    public void testGetAllCoords() {    	
    	List<Coord> expectCoords = new ArrayList<Coord>(); 
    	expectCoords.add(new Coord(1.0, 2.0));
    	expectCoords.add(new Coord(3.0, 4.0));
    	expectCoords.add(new Coord(5.0, 6.0)); 
    	
    	List<Coord> testCoords = service.getAllCoords(); 
    	
    	assertThat(testCoords.size()).isEqualTo(expectCoords.size());
    	
    	for(int i = 0; i < testCoords.size(); i++) {
    		assertThat(testCoords.get(i).getLat())
    		.isEqualTo(expectCoords.get(i).getLat());
    		assertThat(testCoords.get(i).getLng())
    		.isEqualTo(expectCoords.get(i).getLng());
    	}
    	
    }
    
    @Test
    public void testSaveCoord() {
    	Coord expectCoord = new Coord(1.0, 2.0);
    	Coord testCoords = service.createOrSaveCoord(expectCoord); 
    	assertThat(testCoords.getLat()).isEqualTo(expectCoord.getLat());
    	assertThat(testCoords.getLng()).isEqualTo(expectCoord.getLng());
    }
    
    @Test
    public void testFindById() {
    	Coord expectCoord = new Coord(7.0, 8.0);
    	Coord testCoords = service.getCoordById(1).get(); 
    	assertThat(testCoords.getLat()).isEqualTo(expectCoord.getLat());
    	assertThat(testCoords.getLng()).isEqualTo(expectCoord.getLng());
    	
    	Optional<Coord> emptyCoord = service.getCoordById(2);
    	assertThat(emptyCoord).isEmpty(); 
    }
}
