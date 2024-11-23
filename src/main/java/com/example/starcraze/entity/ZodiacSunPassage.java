package com.example.starcraze.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "zodiac_sun_passages")
public class ZodiacSunPassage {
    @Id
    @Column(name = "zodiac_sign")
    private String zodiacSign;

    @Column(name = "passage_description")
    private String passageDescription;

    public String getZodiacSign() {
        return zodiacSign;
    }

    public void setZodiacSign(String zodiacSign) {
        this.zodiacSign = zodiacSign;
    }

    public String getPassageDescription() {
        return passageDescription;
    }

    public void setPassageDescription(String passageDescription) {
        this.passageDescription = passageDescription;
    }
}
