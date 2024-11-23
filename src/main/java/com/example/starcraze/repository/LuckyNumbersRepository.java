package com.example.starcraze.repository;

import com.example.starcraze.entity.LuckyNumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LuckyNumbersRepository extends JpaRepository<LuckyNumbers, Integer> {
    LuckyNumbers findByZodiacSign(String zodiacSign);
}
