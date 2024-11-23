package com.example.starcraze.repository;

import com.example.starcraze.entity.ZodiacSunPassage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZodiacSunPassageRepository extends JpaRepository<ZodiacSunPassage, String> {
    ZodiacSunPassage findByZodiacSign(String zodiacSign);
}
