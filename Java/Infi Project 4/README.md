# Library Project

# Project Setup:

Database: 

Aktuell MySQL 
Später PostgreSQL

Language: Java

Orm: Orm Lite

## Ziele:

- [x]  Many2Many Beziehung muss abgebildet sein
- [x]  Import/Export Funktionalität als JSON
- [x]  Menu Auswahl für Eingabe, Ausgabe, Export im Terminal
- [x]  Doku-README in GitHub wo Funktionalität und Menu erklärt wird

## Database Setup:

Buch Tabelle

Kunden Tabelle

Bücherei Arbeiter

Lesungen

### **Zusatzziele:**

Kunden sollen 1-n Bücher ausleihen können

Neuer Kunde sollte erstellt werden können

Neues Buch sollte erstellt werden können

Bücherei Arbeiter verleihen Bücher

Kunden sollen an 1-n Lesungen teilnehmen können

An den Lesungen können 1-n Kunden Teilnehmen

## Funktionalität:

Das Programm gibt dir die Auswahl zwischen developerMode und userMode.

Im derveloper Mode kann man Einträge updaten, löschen und einfügen. Weiters hat man dort die Möglichkeit die Daten als JSON zu exportieren und importieren.

Im user Mode muss man sich mit seiner Kunden Id anmelden und hat dann die Möglichkeit Bücher auszuleihen, Bücher zurückzugeben und an Lesungen teilzunehmen.