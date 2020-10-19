package ru.Mikhail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.Mikhail.model.Car;
import ru.Mikhail.service.CarService;

@RestController
@RequestMapping("cars")
public class CarsController {
    private static final String CARS_PER_PAGE = "3";

    private final CarService carService;

    @Autowired
    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public Page<Car> findPaginated(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = CARS_PER_PAGE) int size,
            @RequestParam(required = false, defaultValue = "") String model,
            @RequestParam(required = false, defaultValue = "0") int maxSpeed,
            @RequestParam(required = false, defaultValue = "0") int mileage) {

        // фильтр по первому заданному параметру
        // для одновременного фильтра по нескольким параметрам надо что-то придумать
        if (!model.isEmpty()) {
            return carService.findByModel(page, size, model);
        }
        if (maxSpeed > 0) {
            return carService.findByMaxSpeed(page, size, maxSpeed);
        }
        if (mileage > 0) {
            return carService.findByMileage(page, size, mileage);
        }
        return carService.findAll(page, size);
    }

    @GetMapping("{id}")
    public Car getOne(@PathVariable Integer id) {
        return carService.getOne(id);
    }

    @PostMapping
    public Car create(@RequestBody Car car) {
        return carService.create(car);
    }

    @PutMapping("{id}")
    public Car update(@PathVariable Integer id, @RequestBody Car car) {
        return carService.update(id, car);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        carService.delete(id);
    }
}
