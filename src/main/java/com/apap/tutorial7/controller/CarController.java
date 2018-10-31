package com.apap.tutorial7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.service.CarService;
import com.apap.tutorial7.service.DealerService;

/**
 * CarController
 *
 */
@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	@PutMapping(value = "/{carId}")
	private String updateCar(
			@PathVariable (value = "carId") long id,
			@RequestParam("brand") String brand,
			@RequestParam("type") String type,
			@RequestParam("price") Long price,
			@RequestParam("amount") Integer amount,
			@RequestParam("dealerId") String dealerId) {
		CarModel car = carService.getCarDetailById(id).get();
		if (car.equals(null)) {
			return "Couldn't find your dealer";
		}
		if(brand!=null) {
			car.setBrand(brand);
		}
		if(type!=null) {
			car.setType(type);
		}
		if(price!=0) {
			car.setPrice(price);
		}
		if(amount!=0) {
			car.setAmount(amount);
		}
		if(dealerId!=null) {
			DealerModel dealer = dealerService.getDealerDetailById(Long.valueOf(dealerId)).get();
			car.setDealer(dealer);
			
		}
		carService.updateCar(id, car);
		return "car update success";
	}
	
	@PostMapping()
	private CarModel addCarSubmit(@RequestBody CarModel car) {
		return carService.addCar(car);
	}
	
	@GetMapping(value = "/{carId}")
	private CarModel viewCar(@PathVariable ("carId") long carId, Model model) {
		CarModel car = carService.getCarDetailById(carId).get();
		car.setDealer(null);
		return car;
	}
	
	@GetMapping()
	private List<CarModel> viewAllCar(Model model) {
		List<CarModel> listCar = carService.getAllCar();
		for(CarModel car: listCar) {
			car.setDealer(null);
		}
		return listCar;
	}
	
	@DeleteMapping(value = "/{carId}")
	private String deleteCar(@PathVariable("carId") long id, Model model) {
		CarModel car = carService.getCarDetailById(id).get();
		carService.deleteCar(car);
		return "car has been deleted";
	}
	
}