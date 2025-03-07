# Table of Contents
- [Introduction](#introduction)
- [Project Requirements](#project-requirements)
	- [Maven Dependencies](#maven-dependencies)
- [How to run](#how-to-run)
- [How to use program](#how-to-use-program)
- [Screenshots](#screenshots)


# Introduction
This utility interacts with the [Trivia Questions Database API](https://opentdb.com/ "Trivia Questions Database API"), allowing users to specify the number of requests to be made. It retrieves trivia questions and saves each response in various formats: [CSV](https://en.wikipedia.org/wiki/Comma-separated_values "CSV"), [JSON](https://en.wikipedia.org/wiki/JSON "JSON"), or prints the output directly to the [console](https://en.wikipedia.org/wiki/Windows_Console "console"). This flexibility enables users to easily integrate trivia data into their applications or analyze it in a preferred format.

## Project Requirements
- This utility is using [Trivia Questions Database API](https://opentdb.com/ "Trivia Questions Database API") as backend.
- Utiliti is written in [Eclipse IDE](https://eclipseide.org/ "Eclipse IDE") as [Maven Project](https://maven.apache.org/ "Maven Project").

#### Maven Dependencies:
- [APACHE HTTP Components](https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient/4.5.14 "APACHE HTTP Components") (version: 4.5.14): for communicating with backend using HTTP protocol.
- [Jackson Databind](http://https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.18.3 "Jackson Databind") (version: 2.18.3): for serialization and deserialization of JSON Objects.
- [OpenCSV](https://mvnrepository.com/artifact/com.opencsv/opencsv/5.10 "OpenCSV") (version: 5.10): for manipulating .csv files.

Trivia Questions Database API URL: https://opentdb.com/api.php
All .csv and .json files are saved at:
`projectRoot\src\main\resources\results folder`

## How to run
1. Download Java:	
`https://www.oracle.com/java/technologies/downloads/#jdk23-windows`
1. Install Java - click on downloaded file and install Java JDK
1. Setup Environment Variables:
	- Windows Key + S and type System Environment Variables
	- Click on Environment Variables Button
	- In "System variables" section, find Path variable and select it, then click "Edit" button.
	- Click "New" and add path to bin directory of our Java JDK (example: C:\Program Files\Java\jdk-20\bin).
	- Create JAVA_HOME variable:
		- Click "New" in "System variables" section.
		- Set variable name as JAVA_HOME
		- Set variable value as path to Java JDK (example: C:\Program Files\Java\jdk-20).
	- Click OK close all dialog boxes.
	- Verify Java Installation by writing following command:
	`java –version`
1. Run task1 .jar file:
- Open Command Prompt:
	- Locate to project root directory:
	`cd C:\downloads\task\task1`
	- Run task1-0.0.1-SNAPSHOT.jar file located in project/target file:
	`java -jar target/task1-0.0.1-SNAPSHOT.jar`



## How to use program

When program is started it runs and listends for following commands:
-    -n 10 -f json
-    -n 5 -f csv
-    -n 15 -f console
-    exit

**-n:** parameters refers how many time API should be called.<br>
**-f :** referes in what type results should be saved.<br>
**-exit:**  the command to exit the program.<br>


##### [NOTICE]
- CSV Files: when .csv file is opened with Microsoft Excel it does not display properly, but when opened .csv file in Google Sheets or any other .csv viewer it works.
- API Constraints: cannot call API consistently n-times, so instead I get n-items in one API call. I got HTTPStatusCode 429 - Too Much Requests.



## Screenshots

- Command Input:
![Image](https://github.com/user-attachments/assets/a057f91d-1239-420f-8405-11eae59ec4df)

- JSON Output:
![Image](https://github.com/user-attachments/assets/5f9686dd-09ee-4118-819d-988943a09e67)

- CSV Output:
![Image](https://github.com/user-attachments/assets/2b57890f-ebbc-4c30-b1b8-3f2168be442f)

- Console Output:
![Image](https://github.com/user-attachments/assets/1defbbb4-221d-413f-93bb-9582fcb55e88)

