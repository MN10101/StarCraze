package com.example.starcraze.controller;

import com.example.starcraze.entity.Horoscope;
import com.example.starcraze.entity.ZodiacInfo;
import com.example.starcraze.services.ZodiacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ZodiacRestController {

    private final ZodiacService zodiacService;

    @Autowired
    public ZodiacRestController(ZodiacService zodiacService) {
        this.zodiacService = zodiacService;
        System.out.println("ZodiacInfo class: " + ZodiacInfo.class);
    }

    @GetMapping("/api/zodiac-info")
    public ZodiacInfo getZodiacInfo(@RequestParam String zodiacSign) {
        return zodiacService.getZodiacInfo(zodiacSign);
    }

    @PostMapping("/api/add-horoscope")
    public Map<String, Object> addHoroscope(@RequestBody List<Map<String, String>> horoscopes) {
        // Convert the incoming JSON to Horoscope entities
        List<Horoscope> horoscopeEntities = horoscopes.stream()
                .map(h -> new Horoscope(h.get("sunSign").toLowerCase(), h.get("horoscope")))
                .collect(Collectors.toList());

        // Store in the database
        zodiacService.addHoroscopes(horoscopeEntities);

        Map<String, Object> response = Map.of(
                "status", 200,
                "success", true,
                "message", "Horoscope data added successfully!"
        );
        return response;
    }

    @GetMapping("/api/horoscope/{zodiacSign}")
    public Map<String, Object> getHoroscope(@PathVariable String zodiacSign) {
        String horoscope = zodiacService.getHoroscope(zodiacSign.toLowerCase());
        ZodiacInfo zodiacInfo = zodiacService.getZodiacInfo(zodiacSign.toLowerCase());

        Map<String, Object> response = Map.of(
                "status", 200,
                "success", true,
                "zodiacSign", zodiacSign,
                "horoscope", horoscope,
                "luckyNumbers", zodiacInfo.getLuckyNumbers(),
                "rulingPlanet", zodiacInfo.getRulingPlanets(),
                "sunPassage", zodiacInfo.getSunPassages()
        );
        return response;
    }
}
