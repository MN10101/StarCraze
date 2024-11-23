package com.example.starcraze.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "zodiac_ruling_planets")
public class ZodiacRulingPlanet {
    @Id
    @Column(name = "zodiac_sign")
    private String zodiacSign;

    @Column(name = "ruling_planet")
    private String rulingPlanet;

    public String getZodiacSign() {
        return zodiacSign;
    }

    public void setZodiacSign(String zodiacSign) {
        this.zodiacSign = zodiacSign;
    }

    public String getRulingPlanet() {
        return rulingPlanet;
    }

    public void setRulingPlanet(String rulingPlanet) {
        this.rulingPlanet = rulingPlanet;
    }
}
