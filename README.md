
# Spring Security Project

This repository contains a Spring project developed to implement **Spring Security**. The goal of this project is to demonstrate how to configure and integrate Spring Security to secure endpoints in a Spring application.

## Features

- **Authentication:** Implementation of form-based authentication, allowing users to log in with credentials.
- **Authorization:** Configuration of permissions to access specific resources within the application.
- **Endpoint Protection:** Control access to different parts of the application, ensuring that only authenticated and authorized users can access certain resources.
- **User Configuration:** Define users and their respective permissions directly within the Spring Security configuration.

## Technologies Used

- **Spring Framework:** Java development framework.
- **Spring Security:** Framework for authentication and access control.
- **Java:** Programming language used in development.
- **Maven:** Build automation tool and dependency management.

## How to Run the Project

### Prerequisites

- Java 8+ installed
- Maven installed

### Steps to Run

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/Machaieie/Spring-Boot-Security.git
   cd Spring-Boot-Security
   ```

2. **Install Dependencies:**
   ```bash
   mvn install
   ```

3. **Configure Spring Security:**
   - The `SecurityConfig.java` file contains the security configuration.
   - Adjust authentication details as needed.

4. **Run the Application:**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the Application:**
   - The application will be running at `http://localhost:8080`.
   - To access protected areas, you will need to log in with the configured credentials.

## Project Structure

- `src/main/java`: Contains the application source code.
  - `SecurityConfig.java`: Spring Security configuration file.
  - `UserController.java`: Example controller with protected endpoints.
- `src/main/resources`: Contains application resources, such as configuration files.

## Contribution

Contributions are welcome! Feel free to open issues and submit pull requests.
