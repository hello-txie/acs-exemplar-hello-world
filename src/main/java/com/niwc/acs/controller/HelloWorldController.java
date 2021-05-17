package com.niwc.acs.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.niwc.acs.model.Coord;
import com.niwc.acs.service.HelloWorldService;

@Controller
@CrossOrigin("*")
public class HelloWorldController {

	@Autowired
	private HelloWorldService service;
	
	@PostMapping(
		value = "/coords",
		consumes = {MediaType.APPLICATION_JSON_VALUE}
	)
	public ResponseEntity<?> addCoord(@RequestBody Coord coord) {
		if(coord != null ) {
			service.createOrSaveCoord(coord);
			return ResponseEntity.ok().build();
		}
		else {
			return ResponseEntity.badRequest().build(); 
		}
	}
		
	@GetMapping(
		value = "/coords/all",
		produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	public ResponseEntity<List<Coord>> getAllCoord() {
		List<Coord> listCoord = service.getAllCoords();
		return new ResponseEntity<>(listCoord, HttpStatus.OK);
	}
}
