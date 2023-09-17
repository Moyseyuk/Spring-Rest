package com.tms.springrest.web;

import com.tms.springrest.model.Car;
import com.tms.springrest.repository.CarRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/car")
@Tag(name = "CarController", description = "This controller for working with table cars")
public class CarController {

    private final CarRepository repository;

    @Tag(name = "Get all", description = "Get all cars")
    @GetMapping
    public List<Car> getAll(){
        List<Car> cars = repository.findAll();
        for (Car car : cars){
            Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CarController.class).getById(car.getId())).withSelfRel();
            car.add(link);
        }
        return cars;
    }

    @Tag(name = "Get by id", description = "Get car by id")
    @GetMapping("/{id}")
    public Car getById(@PathVariable("id") Long id){
        Optional<Car> carFromDB = repository.findById(id);
        Car car = carFromDB.orElse(null);
        return car;
    }

    @Tag(name = "Save", description = "Save car")
    @PostMapping
    public Car save(@RequestBody Car car){
        repository.save(car);
        return car;
    }

    @Tag(name = "Delete", description = "Delete car by id")
    @DeleteMapping("/{carId}")
    public Car delete(@PathVariable("carId") Long carId){
        Optional<Car> carFromDb = repository.findById(carId);
        Car car = carFromDb.orElse(null);
        repository.deleteById(carId);
        return car;
    }

    @Tag(name = "update", description = "Update user by id")
    @PutMapping("{carId}")
    public Car update(@RequestBody Car car, @PathVariable("carId") Long carId){
        Car updatedCar = new Car(carId, car.getModel(), car.getDateOfIssue(), car.getOwner());
        repository.save(updatedCar);
        return updatedCar;
    }

}
