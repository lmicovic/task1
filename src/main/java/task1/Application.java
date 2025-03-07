package task1;

import task1.util.CommandParser;



/*
 * 1) Install Java Development Kit(JDK) on Windows PC
 * 
 * 		- Download Java:     https://www.oracle.com/java/technologies/downloads/#jdk23-windows
 * 		- Install Java:      click on downloaded file and install Java JDK
 * 		- Setup Environment Variables:
 * 
 * 			- Windows Key + S and type System Environment Variables
 * 			- Click on Environment Variables Button
 * 			- In "System variables" section, find Path variable and select it, then click "Edit" button.
 * 			- Click "New" and add path to bin directory of our Java JDK (example: C:\Program Files\Java\jdk-20\bin)
 * 			- Create JAVA_HOME variable:
 * 
 * 				- Click "New" in "System variables" section
 * 				- Set variable name as JAVA_HOME
 * 				- Set varialbe value as path to Java JDK (example: C:\Program Files\Java\jdk-20)
 * 			
 * 			- Click OK close all dialog boxes
 * 
 * 
 * 
 * 		- Verify Java Installation:
 * 
 * 			- In command prompt write:		java -version
 * 
 * 				
 * 2) Run task1 .jar file:
 * 
 * 		- Open Command Prompt:
 * 
 * 			- Locate to project root directory:
 * 
 * 				cd C:\downloads\task\task1
 * 
 * 
 * 			- Run task1-0.0.1-SNAPSHOT.jar file:
 * 
 * 				java -jar target/task1-0.0.1-SNAPSHOT.jar
 * 
 * 	
 * 3) Use the program:
 * 		
 * 		- Program runs and accepts commands until you write exit command.
 * 		- When program runs you can enter following commands in Console:
 * 
 * 			Enter Command: -n 10 -f json
 * 			Enter Command: -n 5 -f csv
 * 			Enter Command: -n 15 -f console
 * 			Enter Command: exit						- to exit program
 * 
 * 			
 * 
 */

// I was using Open Travia API DB API: 	https://opentdb.com/api.php
//
// - Project is made with Maven:
//
//-  Dependencies: 
//
//		- APACHE HTTP: 				for communicating with backend
//		- Jackson Databind: 		for processing JSON Objects (JSON Object Serialization and Deserialization)
//		- OpenCSV:					for manipulating .csv Files
//
//
//		- API URL: https://opentdb.com/api.php
// 		- File Saved: files are saved at task1\src\main\resources\results folder
//
//
// [NOTICE]
//---------------------------------------------------------------------------------
// CSV Files:   			when .csv file is opened with Microsoft Excel it does not work, but when opened .csv file in Google Sheets its working.
// API Constraints: 		cannot call API consistently n-times, so instead I get n-items in one API call. - I got HTTPStatusCode 429 - Too Much Requests
//---------------------------------------------------------------------------------




public class Application {
	
	public static boolean debug = false;
	
	public static void main(String[] args) {
		
		CommandParser.parseCommands();
		
	}
	
}
