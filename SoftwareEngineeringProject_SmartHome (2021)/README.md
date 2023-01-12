# SWE_Projekt: SmartHome-System mit Sensorik-Komponenten

## Autor

Cordola O'Brien

## Beschreibung

Das SmartHome-System ist eine Anwendung, in dem ein Sensorik-Modul prototypisch dargestellt ist.

Das SmartHome hat verschiedene Sensortypen, aktuell Helligkeit, Temperatur und CO2-Gehalt. Dies ist aber erweiterbar.
Über eine GUI werden die Sensoren in einer Liste dargestellt, hier sind folgende Aktionen möglich: Sensor hinzufügen | Sensor-Messwert aktualisieren | Sensor-Standort ändern und speichern | Sensor löschen
Bei Unter- oder Überschreiten von festgelegten tolerierten Messwerten, wird eine Meldung ausgegeben. Dies ist ebenfalls erweiterbar.

## Design und Pattern

Als Architekturmuster wurde das MVC-Modell angewandt.
Hierbei enthält das Model alle fachlichen Daten und Funktionen des Systems in folgenden Klassen: SmartHome | Sensor (abstrakt) | BrightnessSensor | TemperatureSensor | CO2Sensor | NotificationObserver | HighValueNotificationObserver | LowValueNotificationObserver

Der Controller koordiniert das Ganze. Über ihn kann der Nutzer mit dem System interagieren. Der Controller nimmt dabei Benutzereingaben vom View und gibt sie zur weiteren Bearbeitung an Model oder View weiter.
Der View zeigt die grafische Oberfläche mittels drei verschiedenen Klassen: MainView | AddSensorView | NotificationView 

Als Entwurfsmuster wurde das Observer-Pattern angewandt. Das Interface "NotificationObserver" fungiert hier als Beobachter, und das SmartHome als Subjekt.
Der NotificationObserver beobachtet die Messwerte, die vom SmartHome gemessen werden und bei Eintreffen einer bestimmten Bedingung (Unter-/Überschreiten von bestimmten Messwerten), wird das SmartHome vom Observer benachrichtigt.

## Entwicklungs-Tools

* Visual Studio Code
* Java
* Swing
* Maven
* Javadoc
* JUnit

## UML-Diagramm

![alt text](https://github.com/Coconut237/MyProjects/blob/main/SoftwareEngineeringProject_SmartHome%20(2021)/UML_Diagramm.png?raw=true)

## User Interfaces

Es gibt drei verschiedene Frames. Im Folgenden sind Beispiele der Views aufgeführt:

<ins>**Main View**</ins>

![alt text](https://github.com/Coconut237/MyProjects/blob/main/SoftwareEngineeringProject_SmartHome%20(2021)/UserInterfaceScreenshots/MainView.png?raw=true)
-

<ins>**Sensor Hinzufügen**</ins>

![alt text](https://github.com/Coconut237/MyProjects/blob/main/SoftwareEngineeringProject_SmartHome%20(2021)/UserInterfaceScreenshots/AddSensorView.png?raw=true)
-

<ins>**Sensor Meldung**</ins>

![alt text](https://github.com/Coconut237/MyProjects/blob/main/SoftwareEngineeringProject_SmartHome%20(2021)/UserInterfaceScreenshots/AlertView.png?raw=true)







