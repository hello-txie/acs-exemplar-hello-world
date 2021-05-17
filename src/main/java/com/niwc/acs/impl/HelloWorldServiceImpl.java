package com.niwc.acs.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niwc.acs.model.Coord;
import com.niwc.acs.repository.HelloWorldRepository;
import com.niwc.acs.service.HelloWorldService;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {
	
	@Autowired
	private HelloWorldRepository repo; 
	
	public Optional<Coord> getCoordById(int id) {
		return repo.findById(id);
	}
	
	public List<Coord> getAllCoords() {
		return repo.findAll();
	}
	
	public Coord createOrSaveCoord(Coord coord) {
		return repo.save(coord);
	}
	
	public void deleteCoordById(int id) {	
		repo.deleteById(id);
	}

}
