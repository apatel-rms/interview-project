package interviewProject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class LocationParserTests {

	@Test
	public void howManyLocationsAreThere() throws FileNotFoundException, IOException, ParseException {
		LocationParser locationParser = new LocationParser();
		String filePath = "/Users/aspatel/repo/interviewProject/src/interviewProject/interviewData.json";
		List<Location> locations = locationParser.parseLocationsFromJSONFile(filePath);
		assert(locations.size() == 89551);
	}
}
