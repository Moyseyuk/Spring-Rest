package com.tms.springrest.repository;

import com.tms.springrest.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByOwner (String name);

}
