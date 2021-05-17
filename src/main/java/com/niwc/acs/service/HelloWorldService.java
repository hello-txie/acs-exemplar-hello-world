package com.niwc.acs.service;

import java.util.List;
import java.util.Optional;
import com.niwc.acs.model.Coord;

public interface HelloWorldService {

	public Optional<Coord> getCoordById(int id);
	
	public List<Coord> getAllCoords();
	
	public void deleteCoordById(int id);
	
	public Coord createOrSaveCoord(Coord coord);
}
