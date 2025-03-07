package task1.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import task1.Application;
import task1.exception.HttpResponseException;
import task1.model.Result;

/**
 * This component is used to communicate with Backend API.<br>
 * API URL:		https://opentdb.com/api.php?amount=1&category=10&difficulty=easy
 */
public class ConnectionHandler {

	/**
	 * Represents API URL.
	 */
	private final static String apiBaseUrl = "https://opentdb.com/api.php?";
//	private final static String apiUrl = "https://opentdb.com/api.php?amount=1&category=10&difficulty=easy";
	
	
	/**
	 * This command sends HTTP GET Request to Backend, and return List<Result>, or null if there is no results.
	 * @param apiCalls - represents the number of entity that Backend should return in HTTP Response.
	 * @return resultEntities
	 */
	public static List<Result> getRequest(int apiCalls) {
		
		try {
			
			String entityNumber = "amount=" + apiCalls;
			String otherUrlOptions = "&category=10&difficulty=easy";
			String apiUrl = ConnectionHandler.apiBaseUrl + entityNumber + otherUrlOptions;
			
			URL url = new URL(apiUrl);
			
			//-------------------------------------------------------------
			// Send HTTP Get Request to API
			//-------------------------------------------------------------
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();			// Opens connection to defined URL
			connection.setRequestMethod("GET");													// Define HTTP Get method
			connection.setRequestProperty("Accept", "application/json");						// Define HTTP Header - accept: application/json
			
			int responseCode = connection.getResponseCode();									// HTTP Response Code
			String responseMessage = connection.getResponseMessage();							// HTTP Response Message
			
			if(responseCode != 200) {			// ResponseCode: NOT OK
				System.err.println("HTTP Response Error: [" + responseCode + "] - " + responseMessage);
				throw new HttpResponseException("HTTP Response Error: [" + responseCode + "] - " + responseMessage);
			}
			
			// Debug Code - print HTTP ResponseCode and HTTP ResponseMessage
			if(Application.debug == true) {
				System.out.println(responseCode);
				System.out.println(responseMessage);	
			}
			//-------------------------------------------------------------
			
			//-------------------------------------------------------------
			// Read HTTP Response
			//-------------------------------------------------------------
			if(responseCode == 200) {			// ResponseCode: OK
				
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				String inputLine;
				StringBuilder stringBuilder = new StringBuilder();				// Used StringBuilder because it is more efficient to manipulate with String.
				String responseJson = "";
				while((inputLine = br.readLine()) != null) {
					stringBuilder.append(inputLine);
				}
				
				br.close();
				
				responseJson = stringBuilder.toString();		// HTTP Response is stored here in form of String.
				
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode resultsJsonNode = objectMapper.readTree(responseJson).get("results");
				
				// Deserialized JSON Response into ResultEntity
				List<Result> resultEntities = objectMapper.readValue(resultsJsonNode.toString(),  new TypeReference<List<Result>>() {}); 			// TypeReference<List<Response>>() {} construct in Jackson is used to specify the type information for the generic type List<Response> during deserialization
				
				return resultEntities;
				
			}
			//-------------------------------------------------------------
			
		} catch (HttpResponseException | IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}
