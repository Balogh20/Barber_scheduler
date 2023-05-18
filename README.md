For Hungarian verion please [scroll down](#hungarian-version).

# Hairdresser Appointment Booking Application

## Overview
The Hairdresser Appointment Booking Application is a simple Java application developed with JavaFX for the graphical user interface and H2 Database for data persistence. The application allows customers to book appointments with barbers and for barbers to view their scheduled appointments.

## Prerequisites
- Java JDK 11 or above
- Maven

## How to Run
1. Clone the repository.
2. Navigate to the root directory of the project.
3. Run `mvn clean install` to build the project.
4. Run `java -jar target/hairdresser-booking-1.0.jar` to start the application.

## How to Use
1. On the login screen, enter your username and password. Customers and barbers will have their own separate accounts.

### As a customer:
2. After logging in, you will see a welcome screen with your name displayed.
3. Below the welcome message, you will see a list of your upcoming appointments.
4. To book an appointment, select a date from the date picker and click the "Book Appointment" button. For simplicity, the system will automatically assign the first available barber.

### As a barber:
2. After logging in, you will see a welcome screen with your name displayed.
3. Below the welcome message, you will see a list of your upcoming appointments.


## License
The project is licensed under the [MIT License](./LICENSE).

## Hungarian version

# Fodrász időpontfoglalási alkalmazás

## Áttekintés
A fodrász alkalmazás egy egyszerű Java alkalmazás, amelyet JavaFX segítségével fejlesztettek ki a grafikus felhasználói felülethez és H2 adatbázissal az adatok tárolásához. Az alkalmazás lehetővé teszi, hogy az ügyfelek időpontot foglaljanak a fodrászoknál, a fodrászok pedig megtekinthessék a tervezett időpontokat.

## Előfeltételek
- Java JDK 11 vagy magasabb verzió
- Maven

## Futtatás módja
1. Klónozzuk a tárolót.
2. Navigáljon a projekt gyökérkönyvtárába.
3. Futtassa az `mvn clean install` futtatást a projekt elkészítéséhez.
4. Az alkalmazás elindításához futtassuk a `java -jar target/hairdresser-booking-1.0.jar` parancsot.

## Hogyan kell használni
1. A bejelentkezési képernyőn adja meg felhasználónevét és jelszavát. Az ügyfeleknek és a fodrászoknak külön fiókjuk lesz.

### Ügyfélként:
2. A bejelentkezés után egy üdvözlő képernyő jelenik meg, amelyen az Ön neve látható.
3. Az üdvözlő üzenet alatt megjelenik a közelgő időpontok listája.
4. Időpontfoglaláshoz válasszon ki egy időpontot a dátumválasztóból, és kattintson az "Időpontfoglalás" gombra. Az egyszerűség kedvéért a rendszer automatikusan az első szabad fodrászt jelöli ki.

### Fodrászként:
2. A bejelentkezés után egy üdvözlő képernyő jelenik meg, amelyen az Ön neve látható.
3. Az üdvözlő üzenet alatt megjelenik a közelgő időpontok listája.

## Licenc
A projekt a [MIT License](./LICENSE) licenc alatt áll.