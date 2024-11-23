package com.example.starcraze.entity;

public class ZodiacInfo {
    private String luckyNumbers;
    private String rulingPlanets;
    private String sunPassages;

    // Default constructor
    public ZodiacInfo() {
    }

    // Parameterized constructor
    public ZodiacInfo(String luckyNumbers, String rulingPlanets, String sunPassages) {
        this.luckyNumbers = luckyNumbers;
        this.rulingPlanets = rulingPlanets;
        this.sunPassages = sunPassages;
    }

    // Getters and Setters
    public String getLuckyNumbers() {
        return luckyNumbers;
    }

    public void setLuckyNumbers(String luckyNumbers) {
        this.luckyNumbers = luckyNumbers;
    }

    public String getRulingPlanets() {
        return rulingPlanets;
    }

    public void setRulingPlanets(String rulingPlanets) {
        this.rulingPlanets = rulingPlanets;
    }

    public String getSunPassages() {
        return sunPassages;
    }

    public void setSunPassages(String sunPassages) {
        this.sunPassages = sunPassages;
    }

    // Updated toString method
    @Override
    public String toString() {
        return "ZodiacInfo{" +
                "luckyNumbers='" + luckyNumbers + '\'' +
                ", rulingPlanets='" + rulingPlanets + '\'' +
                ", sunPassages='" + sunPassages + '\'' +
                '}';
    }
}
