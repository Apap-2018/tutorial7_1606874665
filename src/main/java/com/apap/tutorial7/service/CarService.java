package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.CarModel;

/**
 * CarService*/

public interface CarService {
	CarModel addCar(CarModel car);
	void deleteCar(CarModel car);
	CarModel getCar(Long id);
	void updateCar(long id,CarModel car);
	Optional<CarModel> getCarDetailById(Long id);
	List<CarModel> getAllCar();
}
