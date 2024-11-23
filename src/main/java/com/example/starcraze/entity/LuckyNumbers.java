package com.example.starcraze.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "luckynumbers")
public class LuckyNumbers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "zodiac_sign")
    private String zodiacSign;

    @Column(name = "lucky_number1")
    private Integer luckyNumber1;

    @Column(name = "lucky_number2")
    private Integer luckyNumber2;

    @Column(name = "lucky_number3")
    private Integer luckyNumber3;

    @Column(name = "lucky_number4")
    private Integer luckyNumber4;

    @Column(name = "lucky_number5")
    private Integer luckyNumber5;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZodiacSign() {
        return zodiacSign;
    }

    public void setZodiacSign(String zodiacSign) {
        this.zodiacSign = zodiacSign;
    }

    public Integer getLuckyNumber1() {
        return luckyNumber1;
    }

    public void setLuckyNumber1(Integer luckyNumber1) {
        this.luckyNumber1 = luckyNumber1;
    }

    public Integer getLuckyNumber2() {
        return luckyNumber2;
    }

    public void setLuckyNumber2(Integer luckyNumber2) {
        this.luckyNumber2 = luckyNumber2;
    }

    public Integer getLuckyNumber3() {
        return luckyNumber3;
    }

    public void setLuckyNumber3(Integer luckyNumber3) {
        this.luckyNumber3 = luckyNumber3;
    }

    public Integer getLuckyNumber4() {
        return luckyNumber4;
    }

    public void setLuckyNumber4(Integer luckyNumber4) {
        this.luckyNumber4 = luckyNumber4;
    }

    public Integer getLuckyNumber5() {
        return luckyNumber5;
    }

    public void setLuckyNumber5(Integer luckyNumber5) {
        this.luckyNumber5 = luckyNumber5;
    }

    @Override
    public String toString() {
        return String.format("LuckyNumber1: %d, LuckyNumber2: %d, LuckyNumber3: %d, LuckyNumber4: %d, LuckyNumber5: %d",
                luckyNumber1, luckyNumber2, luckyNumber3, luckyNumber4, luckyNumber5);
    }
}
