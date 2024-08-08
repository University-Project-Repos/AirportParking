# SENG201 Airport Parking App

CLI and GUI desktop application for simulating checking vehicles in and out of
[Christchurch International Airport parking lots](https://www.christchurchairport.co.nz/en/parking-and-transport/parking/),
and generating the fee amount for any duration of parking.

![chch_airport](https://github.com/user-attachments/assets/3112c1a4-3fad-46a9-97da-51a66a34d67b)

My first Java project, and only just not flunked if not for the last-moment discovery of an invisible IDE import bug randomly misleading results. 
The application is since refactored when migrating to Java 16 with an aesthetically improved and more robust JavaFX GUI. 
Being a university project for introductory software engineering, the algorithmic computing of parking costs is not updated to reflect changes in official prices. 
The amount of parking spaces available for each parking lot is also not a correct representation.

# Instructions

## Requirements

- Java 16
- Maven

## Test

```bash
mvn clean test
```

alternatively, [click here](../../actions/workflows/test_airport_parking.yml)

## Build

```bash
mvn clean compile assembly:single
```

## Run

### CLI

```bash
java -jar target/CIAL-1.0-jar-with-dependencies.jar
```

### GUI

```bash
java -jar target/CIAL-1.0-jar-with-dependencies.jar gui
```

The GUI should display similar to the following:

![GUI](https://github.com/user-attachments/assets/b63c5ced-bf0b-4421-98ba-3f828911d882)
