create database `starcrazedb`;
USE `starcrazedb`;

CREATE TABLE luckynumbers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    zodiac_sign VARCHAR(50) NOT NULL,
    lucky_number1 INT,
    lucky_number2 INT,
    lucky_number3 INT,
    lucky_number4 INT,
    lucky_number5 INT,
    UNIQUE (zodiac_sign)
);

INSERT INTO luckynumbers (zodiac_sign, lucky_number1, lucky_number2, lucky_number3, lucky_number4, lucky_number5) 
VALUES 
('Aries', 5, 12, 23, 34, 45),
('Taurus', 6, 15, 24, 33, 42),
('Gemini', 7, 14, 21, 28, 35),
('Cancer', 2, 11, 20, 29, 38),
('Leo', 1, 10, 19, 28, 37),
('Virgo', 3, 12, 21, 30, 39),
('Libra', 4, 13, 22, 31, 40),
('Scorpio', 8, 17, 26, 35, 44),
('Sagittarius', 9, 18, 27, 36, 45),
('Capricorn', 10, 19, 28, 37, 46),
('Aquarius', 11, 20, 29, 38, 47),
('Pisces', 12, 21, 30, 39, 48);
