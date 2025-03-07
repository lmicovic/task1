package task1.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.opencsv.CSVWriter;

/**
 * Handles Results that are fetched from Backend.
 */
public class ResultHandler {

	/**
	 * Saves Result Entity based on specific file type.
	 * @param results - resultsEntity that should be saved
	 * @param fileType	- defined File Type (CSV,JSON,Console)
	 * @param fileLocation - location of file where Results should be saved.
	 */
	public static void handleResults(List<Result> results, String fileType) {
		
		try {

			switch (fileType) {
			case "csv":
				ResultHandler.handleCSVFile(results);
				break;
			case "json":
				ResultHandler.handleJsonFile(results);
				break;
			case "console":
				ResultHandler.handleConsole(results);
				break;
			default:
				throw new Exception("Wrong command: " + fileType);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Create and save Results to .csv file.
	 * @param results - result that should be saved
	 * @param fileLocation - location of file where Results should be saved
	 */
	private static void handleCSVFile(List<Result> results) {

		try {

			// Create CSV File
			File newFile = ResultHandler.createFile(results, "csv");
			
			// Write CSV File
			CSVWriter writer = new CSVWriter(new FileWriter(newFile.getAbsolutePath()));
			
			// Get Headers based on Result.class attribute name
			Class<Result> resultClass = Result.class;
			Field[] fields = resultClass.getDeclaredFields();
			
			String[] headers = new String[fields.length];
			for(int i = 0; i < fields.length; i++) {
				headers[i] = fields[i].getName(); 
			}
			
			writer.writeNext(headers, true);
			
			// Write Result Data to Rows in CSV File;
			for (Result result: results) {
				
				// Get list of incorect_answers
				List<String> listIncorrecntAnswers = result.getIncorrect_answers();
				String stringIncorectAnwers = "";
				for (String incorrectAnswer : listIncorrecntAnswers) {
					stringIncorectAnwers += incorrectAnswer + ",";
				}
				stringIncorectAnwers = stringIncorectAnwers.substring(0, stringIncorectAnwers.length() - 1);
				
				String[] rowData = {result.getType(), result.getDifficulty(), result.getCategory(), result.getQuestion(), result.getCorrect_answer(), stringIncorectAnwers};
				
				writer.writeNext(rowData, true);		// Writes data to CSV		
				
			}
			
			writer.close();
			
			System.out.println("File saved at: " + newFile.getAbsolutePath());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Create and write Results to .json file.
	 * 
	 * @param results - results to be saved
	 * @param fileLocation - location where file should be saved
	 */
	private static void handleJsonFile(List<Result> results) {
		
		try {
			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);		// Makes serialized JSON String looks better - prettified.
			
			String json = objectMapper.writeValueAsString(results);
			
			// Create JSON File
			File newFile = ResultHandler.createFile(results, "json");
			
			FileWriter fw = new FileWriter(newFile);
			fw.write(json);
			
			fw.close();
			
			System.out.println("File saved at: " + newFile.getAbsolutePath());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Prints Resulst on Console
	 * @param results - results that should be printed on Console
	 */
	private static void handleConsole(List<Result> results) {
		
		// Print results to Console
		for (Result result : results) {
			System.out.println(result);
		}
		
	}
	
	/**
	 * Create file to specific location in file system.
	 * @param results - Entity objects that have to be saved.
	 * @param fileLocation - location where file should be saved.
	 * @param fileExtension - extension of file that have to be saved.
	 * @return savedFile - saved file or null if saving file is not possible.
	 */
	private static File createFile(List<Result> results, String fileExtension) {
		
		File newFolder = new File("src/main/resources/results/");
		if(newFolder.exists() == false) {
			newFolder.mkdir();
		}
		
		try {
			
			File newFile = new File("src/main/resources/results/result1." + fileExtension);
			int count = 1;
			while(newFile.exists() == true) {
				newFile = new File("src/main/resources/results/result" + count + "." + fileExtension);
				count++;
			}
			
			if(newFile.exists() == false) {
				newFile.createNewFile();
			}
			
			return newFile;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}
