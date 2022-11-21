package com.br.main.controller;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.br.main.model.Car;
import com.br.main.model.Log;
import com.br.main.service.CarServiceImplements;

@RestController
@RequestMapping("/api")
public class CarController {

	private CarServiceImplements carService;
	Queue<Car> queuecar = new LinkedList<>();

	public CarController(CarServiceImplements carService) {
		this.carService = carService;
	}

	@GetMapping(value="/listcars")
	public String listAll() {
		String url = "http://api-test.bhut.com.br:3000/api/cars";
		RestTemplate restTemplate = new RestTemplate();
		
		String cars = restTemplate.getForObject(url, String.class);
		
		carService.generateLog(new Log());
		return cars;
	}

	@PostMapping(value="/createcar")
	public ResponseEntity<String> registerCar(@RequestBody @Validated Car car) {
		this.carService.registerCar(car);
		return carService.webhook();
		 
	}
	
}
