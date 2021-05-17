package com.niwc.acs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niwc.acs.model.Coord;

public interface HelloWorldRepository extends JpaRepository<Coord, Integer> {

}
