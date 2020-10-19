package ru.Mikhail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import ru.Mikhail.exception.NotFoundException;
import ru.Mikhail.model.Car;
import ru.Mikhail.repositories.CarRepository;

@Service
public class CarService {
    private static final String SORT_FIELD = "id";

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Page<Car> findAll(int page, int size) {
        if (page > -1) {
            Pageable pageable = createPageable(page, size);
            Page<Car> resultPage = carRepository.findAll(pageable);
            if (page == 0 || page < resultPage.getTotalPages()) {
                return resultPage;
            }
        }
        throw new NotFoundException("Индекс страницы должен быть меньше количества страниц");
    }

    private Pageable createPageable(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        // если size > 0, то вернем page страницу с не более size записей,
        // иначе вернем все записи на одной странице
        return size > 0 ? PageRequest.of(page, size, sort) : Pageable.unpaged();
    }

    public Page<Car> findByModel(int page, int size, String model) {
        Pageable pageable = createPageable(page, size);
        return carRepository.findByModel(pageable, model);
    }

    public Page<Car> findByMaxSpeed(int page, int size, int maxSpeed) {
        Pageable pageable = createPageable(page, size);
        return carRepository.findByMaxSpeed(pageable, maxSpeed);
    }

    public Page<Car> findByMileage(int page, int size, int mileage) {
        Pageable pageable = createPageable(page, size);
        return carRepository.findByMileage(pageable, mileage);
    }

    public Car getOne(Integer id) {
        return getCar(id);
    }

    private Car getCar(Integer id) {
        return carRepository
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public Car create(Car car) {
        carRepository.save(car);
        return car;
    }

    public Car update(Integer id, Car car) {
        Car carFromDb = getCar(id);
        carFromDb.setModel(car.getModel());
        carFromDb.setMaxSpeed(car.getMaxSpeed());
        carFromDb.setMileage(car.getMileage());
        carRepository.save(carFromDb);

        return carFromDb;
    }

    public void delete(Integer id) {
        try {
            carRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException(ex.getMessage());
        }
    }
}
