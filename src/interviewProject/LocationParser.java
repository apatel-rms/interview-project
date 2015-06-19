package interviewProject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class LocationParser {
	
	public List<Location> parseLocationsFromJSONFile(String filePath) throws FileNotFoundException, IOException, ParseException{
		JSONParser jsonParser = new JSONParser();
		Object obj = jsonParser.parse(new FileReader(filePath));
		JSONArray jsonArray = (JSONArray)obj;
		return parseLocationsFromJSON(jsonArray);
	}
	
	public List<Location> parseLocationsFromJSON(JSONArray locationsJSONArray){
		List<Location> locations = new ArrayList<Location>();
		for (Object locationObject : locationsJSONArray) {
			JSONObject jsonLocation = (JSONObject) locationObject;
			Location location = new Location(jsonLocation);
			locations.add(location);
		}
		return locations;
	}
}
