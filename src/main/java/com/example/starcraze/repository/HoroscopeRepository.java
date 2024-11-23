package com.example.starcraze.repository;

import com.example.starcraze.entity.Horoscope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HoroscopeRepository extends JpaRepository<Horoscope, Long> {
    Optional<Horoscope> findBySunSign(String sunSign);
}
