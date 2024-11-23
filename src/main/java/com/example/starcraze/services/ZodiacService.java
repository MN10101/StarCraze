package com.example.starcraze.services;

import com.example.starcraze.repository.HoroscopeRepository;
import com.example.starcraze.repository.LuckyNumbersRepository;
import com.example.starcraze.repository.ZodiacRulingPlanetRepository;
import com.example.starcraze.repository.ZodiacSunPassageRepository;
import com.example.starcraze.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class ZodiacService {

    private static final Logger logger = LoggerFactory.getLogger(ZodiacService.class);

    private final LuckyNumbersRepository luckyNumbersRepository;
    private final ZodiacRulingPlanetRepository zodiacRulingPlanetRepository;
    private final ZodiacSunPassageRepository zodiacSunPassageRepository;
    private final HoroscopeRepository horoscopeRepository;

    @Autowired
    public ZodiacService(LuckyNumbersRepository luckyNumbersRepository, ZodiacRulingPlanetRepository zodiacRulingPlanetRepository,
                         ZodiacSunPassageRepository zodiacSunPassageRepository, HoroscopeRepository horoscopeRepository) {
        this.luckyNumbersRepository = luckyNumbersRepository;
        this.zodiacRulingPlanetRepository = zodiacRulingPlanetRepository;
        this.zodiacSunPassageRepository = zodiacSunPassageRepository;
        this.horoscopeRepository = horoscopeRepository;
    }

    public void addHoroscopes(List<Horoscope> horoscopes) {
        horoscopeRepository.saveAll(horoscopes);
    }

    public String getHoroscope(String sunSign) {
        Optional<Horoscope> horoscope = horoscopeRepository.findBySunSign(sunSign.toLowerCase());
        return horoscope.map(Horoscope::getHoroscope).orElse("No horoscope found for this sign");
    }

    public ZodiacInfo getZodiacInfo(String zodiacSign) {
        LuckyNumbers luckyNumbersEntity = luckyNumbersRepository.findByZodiacSign(zodiacSign);
        ZodiacRulingPlanet rulingPlanetEntity = zodiacRulingPlanetRepository.findByZodiacSign(zodiacSign);
        ZodiacSunPassage sunPassageEntity = zodiacSunPassageRepository.findByZodiacSign(zodiacSign);

        String luckyNumbers = "No lucky numbers found";
        String rulingPlanets = "No ruling planet found";
        String sunPassages = "No sun passage found";

        if (luckyNumbersEntity != null) {
            luckyNumbers = String.format("Lucky Numbers: %d, %d, %d, %d, %d",
                    luckyNumbersEntity.getLuckyNumber1(),
                    luckyNumbersEntity.getLuckyNumber2(),
                    luckyNumbersEntity.getLuckyNumber3(),
                    luckyNumbersEntity.getLuckyNumber4(),
                    luckyNumbersEntity.getLuckyNumber5());
        }

        if (rulingPlanetEntity != null) {
            rulingPlanets = "Ruling Planet: " + rulingPlanetEntity.getRulingPlanet();
        }

        if (sunPassageEntity != null) {
            sunPassages = "Sun Passage: " + sunPassageEntity.getPassageDescription();
        }

        return new ZodiacInfo(luckyNumbers, rulingPlanets, sunPassages);
    }
}

