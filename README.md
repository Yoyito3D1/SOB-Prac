# 🎮 Retro Game Rental System 🎮
Welcome to this project where we build a complete system for renting retro video games! 🚀  
This repository contains two main parts: a RESTful API for managing rentals and a web interface to interact with the system.

---

## 🎯 Project Overview
This project demonstrates step-by-step development of a video game rental system:

- **Homework 1:** Backend REST API to manage games, rentals, and users.
- **Homework 2:** Frontend web interface using JSP, MVC pattern, and interactive features.

The system combines Java, JPA, RESTful services, JSP, and session management to create a functional and secure rental platform. 💪✨

---

## Homework 1 – REST API for Game Rentals

### Baseline Solution
- **Backend:** Java + JPA
- **REST API Endpoints:** Game, Customer, Rental
- **Security:** HTTP Basic Authentication

### Key Features
- **Games:** List games in JSON format, filter by type or console.
- **Rentals:** Create rentals and retrieve receipt details, total price, and return date.
- **Users:** List and update customer information (authenticated only).

### Design Decisions
- **Many-to-many** relationship between games and customers.
- Sequence generation for IDs with `.SEQUENCE` for Game and Customer.
- Exception handling with try/catch for robust API responses.
- Standard JSON format for communication between client and server.

### Testing & Validation
- Tested GET and POST endpoints using Postman.
- Validated creation and retrieval of games, customers, and rentals.
- Handled errors like 405 (method not allowed) and missing IDs.

### Installation & Usage
1. Start your database.
2. Perform a **clean build** of the project.
3. Run the application.
4. Use `install.jsp` or Postman to insert initial data.

---

## Homework 2 – Web Interface for Game Rentals

### Frontend Solution
- **Technologies:** JSP, JSTL, HTML, CSS, JavaScript
- **Pattern:** Model-View-Controller (MVC)
- **Controllers:** FrontPageController, GameInfoController, CarritoController, LoginController

### Key Features
- **MainPage:** Display and filter games dynamically without reloading.
- **GameInfo Page:** Responsive design with game details and “Add to Cart” functionality.
- **Shopping Cart:** Add/remove products and process payments securely.
- **Login:** Secure login with HTTPS, session management, and error feedback.

### Design Decisions
- MVC for clear separation between View, Controller, and Model.
- Dependency injection via `@Inject` for modularity and testability.
- JavaScript DOM manipulation for interactive user experience.
- Sessions and cookies to manage authentication and user state.
- Accessibility and responsive layout with CSS Flexbox and Grid.

### User Experience & Security
- Smooth, interactive browsing and filtering of games.
- Clear messages on errors and authentication issues.
- Session-based security ensures only logged-in users can modify data.
- Designed for usability across devices and accessibility standards.

---

## 🛠️ How to Run
1. Configure and start your database.
2. Build the project using Maven or your preferred Java IDE.
3. Run the project on a Java-compatible server (e.g., Tomcat).
4. Access endpoints via Postman (Homework 1) or browse the JSP interface (Homework 2).

---

## 📈 Results & Insights
- Homework 1 provides a robust REST API ready for integration.
- Homework 2 offers a modular, secure, and interactive web interface.
- Combining both creates a full-featured system ready for future extensions.
- Security, session management, and user experience were key design priorities.

---

## 🙌 Contributions & Credits
- Developed as part of **Open Systems (Sistemes Oberts)** coursework.
- Inspired by retro video games like Super Mario Land.
- Thanks to open source tools: Java, JPA, JSP, JSTL, CSS, and JavaScript.

---

## 📫 Contact & Support
If you want to discuss the project or contribute, feel free to open an issue or reach out! ✉️  

Thank you for checking out this project! 🚀  
Let’s keep building secure and interactive web systems together! 💡
