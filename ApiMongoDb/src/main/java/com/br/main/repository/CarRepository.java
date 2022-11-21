package com.br.main.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.br.main.model.Car;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {

}
