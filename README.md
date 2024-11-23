
# 🌟 Star Craze App 🌟

Star Craze is an engaging astrology-based web application that provides personalized horoscopes, lucky numbers, zodiac ruling planets, and sun passage insights. Whether you're a casual astrology enthusiast or someone deeply interested in celestial wisdom, Star Craze has something for everyone.

---

## ✨ Features ✨

- **Monthly Horoscopes**: Get insightful horoscopes tailored to your zodiac sign.
- **Lucky Numbers**: Discover your set of lucky numbers based on your zodiac sign.
- **Ruling Planets**: Learn about the celestial body governing your zodiac sign.
- **Sun Passages**: Explore the unique sun passage for each zodiac sign.
- **Testimonials Section**: Engage with user feedback to see how others connect with astrology.
- **Responsive Design**: Works seamlessly on all devices, including desktop, tablet, and mobile.

---

## 🚀 Getting Started 🚀

Follow these instructions to set up and run the Star Craze App locally.

### Prerequisites

Ensure you have the following installed on your system:
- **Java 17+**
- **Spring Boot 3.0+**
- **Maven 3.6+**
- **MySQL 8.0+**
- **Docker**

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/star-craze.git
   cd star-craze
   ```

2. Set up the database:
   - Create a new MySQL database:
     ```sql
     CREATE DATABASE starcraze;
     ```
   - Update the database connection properties in `src/main/resources/application.properties`:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/starcraze
     spring.datasource.username=yourusername
     spring.datasource.password=yourpassword
     ```

3. Build the application:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

5. Access the app in your browser at `http://localhost:8080`.

---

## 🗂️ Directory Structure 🗂️

```
Star Craze/
├── src/
│   ├── main/
│   │   ├── java/com/example/starcraze/
│   │   │   ├── controller/        # REST Controllers
│   │   │   ├── entity/             # Entity Classes
│   │   │   ├── repository/        # JPA Repositories
│   │   │   ├── security/           # Access: security
│   │   │   ├── services/           # Business Logic Services
│   │   └── resources/
│   │       ├── application.properties  # Configuration File
│   │       ├── templates/              # HTML Templates (if applicable)
│   │       ├── static/                 # Static Assets (CSS, JS, Images)
└── README.md
```

---

## 🛠️ Technologies Used 🛠️

### Backend:
- **Java**
- **Spring Boot**
- **Spring Mail**
- **Hibernate/JPA**
- **MySQL**


### Frontend:
- **HTML/CSS**
- **JavaScript**
- **Bootstrap**

### Tools:
- **Maven** for project management
- **Authentications** for logging
- **Postman** for API testing

---

## 🌌 API Endpoints 🌌

### Zodiac Info
- **GET /api/horoscope/{zodiacSign}**
- ** URL http://localhost:8080/api/horoscope/aries**
- Fetch monthly horoscope and detailed zodiac information.

### Add Horoscopes
- **POST /api/horoscope/add**  
  Add a new set of horoscopes to the database.

---

##   Customization 🔧

You can modify the content for zodiac-related data by updating the corresponding database tables:
- **`luckynumbers`**: Lucky numbers for each zodiac.
- **`zodiac_ruling_planets`**: Governing planet for each zodiac.
- **`zodiac_sun_passages`**: Sun passage descriptions.

---

## 🌟 Future Enhancements 🌟

- Add user authentication and profiles.
- Introduce compatibility and relationship insights.
- Expand horoscope content with career, love, and health readings.
- Integrate with social media for sharing.

---


## 🤝 Contributing 🤝

We welcome contributions! If you'd like to improve Star Craze, please fork the repository and submit a pull request. Make sure to follow our [contribution guidelines](CONTRIBUTING.md).

---

## 📧 Contact 📧

For queries, feedback, or support, feel free to reach out:
- Email: star.craze.service@gmail.com
- GitHub: [Star Craze Repository](https://github.com/yourusername/star-craze)
