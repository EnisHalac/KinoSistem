# Kino Sistem – Movie & Ticket

## Članovi tima
- Halac Enis
- Jasarevic Ahmed

## Opis aplikacije
Ova Spring Boot aplikacija implementira **mini kino sistem** koristeći **MVC arhitekturu** i **Thymeleaf** za prikaz web stranica.  
Omogućava pregled filmova, listu svih prodatih karata, te kupovinu karata direktno preko web stranice.

### Modeli

#### Movie
- **Atributi:**
  - `id` (Long) – jedinstveni identifikator filma
  - `title` (String) – naziv filma
  - `genre` (String) – žanr filma
  - `duration` (int) – trajanje filma u minutama
  - `rating` (double) – ocjena filma
  - `price` (double) – cijena karte
  - `imageUrl` (String) – putanja do slike/postera filma

#### Ticket
- **Atributi:**
  - `id` (Long) – jedinstveni identifikator karte
  - `seatNumber` (String) – broj sjedala
  - `price` (double) – cijena karte
  - `customerName` (String) – ime kupca
  - `movie` (Movie) – film za koji je kupljena karta

### Relacija
- **1:N** – Jedan film (`Movie`) može imati više kupljenih karata (`Ticket`).

## Rute aplikacije
- `/movies` – prikaz liste svih filmova  
- `/tickets` – prikaz liste svih prodatih karata  
- `/movie/action/{id}` – detalji o filmu i forma za kupovinu karata  

## Screenshot aplikacije

![Screenshot Movie List](https://github.com/EnisHalac/KinoSistem/blob/main/src/Screenshot1.PNG)  
![Screenshot Tickets](https://github.com/EnisHalac/KinoSistem/blob/main/src/Screenshot2.PNG)  
![Screenshot Movie Details](https://github.com/EnisHalac/KinoSistem/blob/main/src/Screenshot3.PNG)  

---

**Napomena:** Aplikacija koristi **in-memory podatke** (`DemoData.java`), tako da nije potrebna baza podataka.  

