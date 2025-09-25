# Kontoverwaltung - GUI Einbindung

Author: Soey Schober

LBS Eibiswald | 2aAPC

Dieses Projekt zeigt, wie man mit Vererbung und Polymorphie verschiedene Arten von Bankkonten modellieren kann. Es existieren unterschiedliche Konto-Klassen (z. B. Girokonto, Sparkonto, Kreditkonto), die von einer gemeinsamen Basisklasse erben.

## Grundlagen zum Projektaufbau

1. Erstellung einer abstrakten Basisklasse `Konto` mit den wichtigsten Attributen und Methoden.
2. Ableitung spezieller Kontoarten wie `Girokonto`, `Sparkonto` und `Kreditkonto`.
3. Implementierung von Überziehungslimit, Zinsen oder speziellen Regeln je nach Kontotyp.
4. Im `Main`-Programm werden die Konten erzeugt, getestet und deren Verhalten demonstriert.

## Verwendung

### Technologien im Einsatz:

-Java

-IntelliJ IDEA

### Start der Anwendung:

Beim Start des `Main`-Programms werden die Konten erzeugt und Methoden wie Einzahlen, Abheben oder Zinsen berechnen getestet.

Die Konsolenausgabe zeigt den Ablauf der Aktionen (z. B. Kontostände, Fehlermeldungen bei Überziehung usw.).

## Output (Konto anlegen, Überweisen & Konto auflösen) - Console

### Konto anlegen

<p>
  <img src="https://github.com/user-attachments/assets/0b0cd251-c489-402e-96ba-81fe9d45009f" alt="image" width="40%">
  <img src="https://github.com/user-attachments/assets/76962a6f-04a3-42ff-903c-04ed98e991b1" alt="image" width="50%">
</p>

### Überweisen 

(mit Überprüfung)

<img width="359" height="377" alt="image" src="https://github.com/user-attachments/assets/f8c87a53-0bd3-42ee-9e71-c2e6256d3675" />

<p>
  <img width="295" height="264" alt="image" src="https://github.com/user-attachments/assets/04ea76fd-8f9f-4302-8781-669f01829eb3" />
  <img width="462" height="257" alt="image" src="https://github.com/user-attachments/assets/4c3f91b3-e608-4f23-9dfb-5f2e33ee85e6" />
</p>

### Konto auflösen

(mit Überprüfung)

<img width="376" height="665" alt="image" src="https://github.com/user-attachments/assets/e01ecfbb-a32f-4d5e-8b41-56124300aa29" />

## Output (Konto anlegen, Konto wechseln) - mit GUI

### Start

<img width="787" height="482" alt="image" src="https://github.com/user-attachments/assets/deb3be9c-e16a-45d0-a8d5-62f5b84f1db5" />


### Konto anlegen

<img width="782" height="479" alt="image" src="https://github.com/user-attachments/assets/3c875d07-9ddf-4403-ba1b-e35c579f8e62" />
<img width="788" height="480" alt="image" src="https://github.com/user-attachments/assets/e07023b8-24ae-45b6-aecc-f7a82e282b8e" />

### Konto wechseln

<img width="785" height="480" alt="image" src="https://github.com/user-attachments/assets/b99aaaae-16c7-4f3d-b832-a312c1ff4cf3" />
<img width="783" height="475" alt="image" src="https://github.com/user-attachments/assets/dfb2ac8a-e458-4548-9b0d-e20f90faef8c" />

