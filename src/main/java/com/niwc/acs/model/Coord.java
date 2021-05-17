package com.niwc.acs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coords")
public class Coord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
	private double lat;
	private double lng;
	
	public Coord() {
		
	}
	
	public Coord(double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
	}
	
	public int getId() {
		return id;
	}
	
	public double getLat() {
		return lat;
	}
	
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public double getLng() {
		return lng;
	}
	
	public void setLng(double lng) {
		this.lng = lng;
	}
}
