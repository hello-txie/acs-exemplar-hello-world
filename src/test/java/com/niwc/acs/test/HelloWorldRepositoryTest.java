package com.niwc.acs.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import com.niwc.acs.model.Coord;
import com.niwc.acs.repository.HelloWorldRepository;

@DataJpaTest
public class HelloWorldRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private HelloWorldRepository repo;
	
	@Test
	public void testSave() throws Exception {
		Coord expectCoord = new Coord(123.123, 321.321);
		
		repo.save(expectCoord);
		
		Coord testCoord = entityManager.find(Coord.class, expectCoord.getId());
		
		assertThat(testCoord).isNotNull();
		assertThat(testCoord.getLat()).isEqualTo(expectCoord.getLat());
		assertThat(testCoord.getLng()).isEqualTo(expectCoord.getLng());
	}
	
	@Test
	public void testFindById() throws Exception {
		Coord expectCoord = new Coord(123.123, 321.321);
		entityManager.persist(expectCoord); 
		int expectCoordId = expectCoord.getId();
		
		Coord testCoord = repo.findById(expectCoordId).get(); 
		assertThat(testCoord.getLat()).isEqualTo(expectCoord.getLat());
		assertThat(testCoord.getLng()).isEqualTo(expectCoord.getLng());
		
		entityManager.remove(expectCoord);
		Optional<Coord> testEmptyCoord = repo.findById(expectCoordId);
		assertThat(testEmptyCoord).isEmpty(); 
	}
	
	@Test
	public void testGetAll() throws Exception {
		
		Coord expectCoordOne = new Coord(1.0, 2.0);
		Coord expectCoordTwo = new Coord(3.0, 4.0);
		Coord expectCoordThree = new Coord(5.0, 6.0);
		
		entityManager.persist(expectCoordOne);
		entityManager.persist(expectCoordTwo);
		entityManager.persist(expectCoordThree);
		
		List<Coord> allCoord = repo.findAll(); 
		
		assertThat(allCoord.size()).isEqualTo(3); 
		
		for(int i = 0; i < 3; i++) {
			Coord testCoord = allCoord.get(i); 
			Coord expectCoord = entityManager.find(Coord.class, testCoord.getId());

			assertThat(testCoord.getLat()).isEqualTo(expectCoord.getLat());
			assertThat(testCoord.getLng()).isEqualTo(expectCoord.getLng());
		}
		
	}
	
	@Test
	public void testDelete() throws Exception {
		Coord expectCoord = new Coord(123.123, 321.321);
		entityManager.persist(expectCoord); 
		int expectCoordId = expectCoord.getId();
		
		Coord testCoord = repo.findById(expectCoordId).get(); 
		assertThat(testCoord.getLat()).isEqualTo(expectCoord.getLat());
		assertThat(testCoord.getLng()).isEqualTo(expectCoord.getLng());
		
		repo.deleteById(expectCoordId);
		Coord testEmptyCoord = entityManager.find(Coord.class, expectCoordId);
		assertThat(testEmptyCoord).isNull(); 
	}
	
	@Test
	public void testUpdate() throws Exception {
		Coord expectCoord = new Coord(123.123, 321.321);
		entityManager.persist(expectCoord); 
		int expectCoordId = expectCoord.getId();
		
		Coord testCoord = repo.findById(expectCoordId).get(); 
		assertThat(testCoord.getLat()).isEqualTo(expectCoord.getLat());
		assertThat(testCoord.getLng()).isEqualTo(expectCoord.getLng());
		
		testCoord.setLat(100.0);
		testCoord.setLng(200.0);
		repo.save(testCoord); 
		
		testCoord = repo.findById(expectCoordId).get(); 
		assertThat(testCoord.getLat()).isEqualTo(100.0);
		assertThat(testCoord.getLng()).isEqualTo(200.0);
	}
}
