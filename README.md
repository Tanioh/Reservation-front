# Reservation FrontOffice

## Description
Application publique de réservation de voitures — **FrontOffice**.  
Backend **Spring Boot** + Frontend **Spring MVC** avec **Vue.js**.

## Prérequis
- Java 17+
- PostgreSQL 14+
- Maven 3.8+
- Node.js 18+ (pour le développement Vue.js)

## Configuration
Modifier `src/main/resources/application.properties` avec vos identifiants PostgreSQL.

## Build & Run
```bash
mvn clean spring-boot:run
```

L'application sera disponible sur : http://localhost:8080

## Branches
- `release` → déploiement en production
- `staging` → déploiement local / développement

## Structure
```
reservation-frontoffice/
├── src/main/java/com/reservation/frontoffice/
│   ├── controller/       # Contrôleurs REST + MVC
│   ├── model/            # Entités JPA
│   ├── repository/       # Spring Data JPA
│   ├── service/          # Logique métier
│   └── ReservationFrontofficeApplication.java
├── src/main/resources/
│   ├── static/           # Vue.js (frontend)
│   ├── templates/        # Thymeleaf templates
│   └── application.properties
└── pom.xml
```
