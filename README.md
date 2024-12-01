# Marvel Comics Rental  
_An application to manage and explore Marvel Comics rentals_  

## Introduction  

This repository hosts a full-stack application for renting Marvel Comics.  

- **Backend**: Spring Boot, PostgreSQL, Flyway  
- **Frontend**: React Native with Expo  
- **API Integration**: Utilizes the Marvel API to fetch comic details.  
- **Design Pattern**: Follows the MVC (Model-View-Controller) pattern.  

---

## Requirements  

Install the following:  

- **JDK** 17+  
- **Spring Boot** 3.3.0+  
- **Maven** 3.9.8+  
- **PostgreSQL** 16
- **Expo Go** app on your mobile device  

---

## Features  

1. **Marvel API Integration**: Fetches real-time Marvel Comics data using their official API.  
2. **MVC Architecture**: Organized backend structure using Models, Controllers, and Services.  
3. **DTOs**: Simplifies data transfer between application layers.  
4. **Database Management**: Database structure is managed using Flyway migrations.  

---

## How to Run

### Clone the Repository

1. Clone this repository:
    ```bash
    git clone https://github.com/math-hrque/marvel-comics-rental.git
    ```
2. Open a terminal and navigate to the directory where the project files are located.

### Run the Backend

1. Navigate to the backend folder.

    ```bash
    cd marvel-app-apring
    ```

2. Build and run the backend.

    ```bash
    mvn clean package spring-boot:run
    ```

### Run the Frontend

1. Navigate to the frontend folder.

    ```bash
    cd marvel-app-ui
    ```

2. Install dependencies.

    ```bash
    npm install
    ```

3. Start the frontend.

    ```bash
    npx expo start
    ```

4. Scan the QR code or use the URL provided in the terminal output.

    ```bash
    › Metro waiting on {Enter from here:} exp://[YOUR_NETWORK_IP]
    ```

---

## Database Configuration

- Credentials must be provided in the `application.yml`.
- Example `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://[DB_HOST]:[DB_PORT]/[DB_NAME]
    username: [DB_USER]
    password: [DB_PASSWORD]
```

> Place the `application.yml` file in the `marvel-app-spring` directory.

---

## Notes

- Ensure PostgreSQL is running before starting the backend.
- Flyway will automatically create and manage the database structure.  
- Update the `application.yml` file with the correct database credentials, IP, and ports.
- Place the `application.yml` file in the `marvel-app-spring` directory.
