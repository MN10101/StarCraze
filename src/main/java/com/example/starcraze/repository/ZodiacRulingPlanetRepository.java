package com.example.starcraze.repository;

import com.example.starcraze.entity.ZodiacRulingPlanet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZodiacRulingPlanetRepository extends JpaRepository<ZodiacRulingPlanet, String> {
    ZodiacRulingPlanet findByZodiacSign(String zodiacSign);
}
