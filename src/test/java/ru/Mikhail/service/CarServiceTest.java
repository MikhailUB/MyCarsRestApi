package ru.Mikhail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import ru.Mikhail.model.Car;
import ru.Mikhail.repositories.CarRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    @Test
    public void createAndFindAll() {
        Car car = carRepository.save(new Car("Lada", 150, 44_400));
        Page<Car> page = carService.findAll(0, 0);
        assertEquals(1, page.getContent().stream().filter(c -> c.getId().equals(car.getId())).count());
    }

    @Test
    public void findByModel() {
    }

    @Test
    public void getOne() {
        Car car = carRepository.save(new Car("Niva", 140, 55_500));
        Car oneCar = carService.getOne(car.getId());
        assertEquals(car.getId(), oneCar.getId());
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}
