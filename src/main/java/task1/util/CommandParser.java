package task1.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import task1.Application;
import task1.model.Result;
import task1.model.ResultHandler;

/**
 * This component is used for parsing user input commands from Console Input.
 */
public class CommandParser {
	
	/**
	 * This method is used for parsing User input commands from Console Input.
	 */
	public static void parseCommands() {
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			
			// Read Line from Console Input
			System.out.print("Enter command: ");
	        String input = sc.nextLine().toLowerCase();

	        //----------------------------------------------------------
	        // Commands: EXIT - exits the program
	        //----------------------------------------------------------
	        if(input.equals("exit")) {
	        	sc.close();
	        	System.out.println("Program closed!");
	        	System.exit(0);
	        }
	        //----------------------------------------------------------
	        
	        
	        //----------------------------------------------------------
	        // Parse Input Console Commands
	        //----------------------------------------------------------

	        // Split the input by spaces
	        String[] commandParts = input.split(" ");
	        
	        //------------------------------------------------------------------------------
	        // Validate command
	        //------------------------------------------------------------------------------
	        if (commandParts.length < 4 || commandParts.length > 4) {
	            System.out.println("Invalid command. Please provide all required parameters.");
	            continue;
	        }
	        
	        //------------------------------------------------------------------------------
	        // Parse Command
	        //------------------------------------------------------------------------------
	        
	        // Extract file location from Command Input
//	        String fileLocation = commandParts[0];

	        // Store Command(-n and -f) options and their values
	        Map<String, String> options = new HashMap<>();

	        // Parse the command parts - create Map<> that stores parameter(-n, -f) and their values.
	        for (int i = 0; i < commandParts.length; i++) {
	            String part = commandParts[i];
	            if (part.startsWith("-")) { // Check if it's an option
	                String option = part;
	                String value = ""; // Default value
	                if (i + 1 < commandParts.length && !commandParts[i + 1].startsWith("-")) {
	                    value = commandParts[i + 1]; // Get the value if the next part is not an option
	                    i++; // Skip the value in the next iteration
	                }
	                options.put(option, value); // Store the option and its value
	            }
	        }

	        //------------------------------------------------------------------------------
	        // Validate parameters
	        //------------------------------------------------------------------------------
	        boolean validateParameters = CommandParser.validateParameters(options);
	        
	        // Debug Optional - not important
	        if(Application.debug == true) {
	        	if(validateParameters == true) {
	   	   	     	// Output the parsed values
//	   		        System.out.println("File location: " + fileLocation);
	   		        System.out.println("Parsed options:");
	   		        for (Map.Entry<String, String> entry : options.entrySet()) {
	   		            System.out.println(entry.getKey() + " " + entry.getValue());
	   		        }
	   	        }
	        }
	        	        
	        //--------------------------------------------------------------------
	        // Send HTTP Get Request - if Command is valid send HTTP Get Request to API
	        //--------------------------------------------------------------------
	        if(validateParameters == true) {

	        	int apiCalls = Integer.parseInt(options.get("-n"));
	        	String fileType = options.get("-f");
	        	
	        	// Get Results from Backend
	    		List<Result> results = ConnectionHandler.getRequest(apiCalls);
	    		
	    		// For Debugging purposes - not important
	    		if(Application.debug == true) {
	    			for (Result result : results) {
	    				System.out.println(result);
	    			}	
	    		}
		        //------------------------------------------------------------------------------	        	
	    		// Save Result to File
		        //------------------------------------------------------------------------------
	        	ResultHandler.handleResults(results, fileType);
		        //------------------------------------------------------------------------------
	        	
	        }
	        //------------------------------------------------------------------------------
	        
		}
		
	}
	
	/**
	 * Used for validating input commands.
	 * @param options - input command options(-n: number of API calls, and -f file location for saving result files)
	 * @return - true if command is valid, false if command is invalid
	 */
	private static boolean validateParameters(Map<String, String> options) {
        
		 // Check for -n parameter
		if (options.containsKey("-n")) {
            try {
                int apiCalls = Integer.parseInt(options.get("-n"));
                if (apiCalls <= 0) {
                    System.out.println("Error: Number of API calls must be greater than 0.");
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number format for -n parameter.");
                return false;
            }
        } else {
            System.out.println("Error: Missing -n parameter.");
            return false;
        }

        // Check for -f parameter
        if (options.containsKey("-f")) {
            String fileFormat = options.get("-f").toUpperCase();
            if (!fileFormat.equals("CSV") && !fileFormat.equals("JSON") && !fileFormat.equals("CONSOLE")) {
                System.out.println("Error: Invalid file format. Possible formats are CSV, JSON, CONSOLE.");
                return false;
            }
        } else {
            System.out.println("Error: Missing -f parameter.");
            return false;
        }
	        
        return true;
	        
    }
	
}
