package com.example.starcraze.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "horoscopes")
public class Horoscope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String sunSign;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String horoscope;

    // Constructors
    public Horoscope() {
    }

    public Horoscope(String sunSign, String horoscope) {
        this.sunSign = sunSign;
        this.horoscope = horoscope;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSunSign() {
        return sunSign;
    }

    public void setSunSign(String sunSign) {
        this.sunSign = sunSign;
    }

    public String getHoroscope() {
        return horoscope;
    }

    public void setHoroscope(String horoscope) {
        this.horoscope = horoscope;
    }
}
