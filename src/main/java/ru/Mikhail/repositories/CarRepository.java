package ru.Mikhail.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.Mikhail.model.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {

    Page<Car> findByModel(Pageable pageable, String model);

    Page<Car> findByMaxSpeed(Pageable pageable, int maxSpeed);

    Page<Car> findByMileage(Pageable pageable, int mileage);
}
