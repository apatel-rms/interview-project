package interviewProject;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccumulationEngineTests {

	private List<Location> locations;
	private AccumulationEngine accumulationEngine;
	
	@Before
	public void setup() throws FileNotFoundException, IOException, ParseException {
		this.accumulationEngine = new AccumulationEngine();
		LocationParser parser = new LocationParser();
		String filePath = "/Users/aspatel/repo/interviewProject/src/interviewProject/interviewData.json";
		this.locations = parser.parseLocationsFromJSONFile(filePath);
	}
	
	@Test
	public void howManyLocations(){
		assertEquals(this.locations.size(), 89551);
	}
	
	@Test
	public void howManyCaliforniaLocations() {
		String statecode = "CA";
		List<Location> caLocations = 
				this.accumulationEngine.filterLocationsByState(this.locations, statecode);
		assertEquals(caLocations.size(), 26767);
	}
	
	@Test
	public void howMany90210Locations() {
		String postalCode = "90210";
		List<Location> beverlyHillsLocations = 
				this.accumulationEngine.filterLocationsByPostalCode(this.locations, postalCode);
		assertEquals(beverlyHillsLocations.size(), 26);
	}
	
	@Test
	public void howManyOCCALocations() {
		String stateCode = "CA";
		String countyName = "Orange County";
		List<Location> ocLocations = 
				this.accumulationEngine.filterLocationsByCounty(this.locations, stateCode, countyName);
		assertEquals(ocLocations.size(), 277);
	}
	
	@Test
	public void accumulationOverOCCALocations() {
		String stateCode = "CA";
		String countyName = "Orange County";
		double damageFactor = 0.35;
		double totalValue = 
				this.accumulationEngine.accumulationOverCounty(this.locations, stateCode, countyName);
		assertEquals(totalValue, 1.334597541E9, 0.00001);
		assertEquals(totalValue * damageFactor, 1.334597541E9 * damageFactor, 0.00001);
	}
	
	@Test
	public void whichCountiesInCAAreOverLimit() {
		List<Location> caLocations = 
				this.accumulationEngine.filterLocationsByState(locations, "CA");
		Map<String, Double> countyExposures = 
				this.accumulationEngine.exposureByCounty(caLocations);

		double limit = 100000000;
		List<CountyExposure> overlimitCounties = new ArrayList<CountyExposure>();
		
		Iterator<Entry<String, Double>> it = countyExposures.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, Double> entry = it.next();
			CountyExposure exposure = new CountyExposure(entry.getKey(), entry.getValue());
			if(exposure.getExposure() > limit){
				overlimitCounties.add(exposure);
			}
		}
		for (CountyExposure overExposedCounty : overlimitCounties) {
			System.out.println(String.format("County: %s, Total Exposure: %f", overExposedCounty.getCountyName(), overExposedCounty.getExposure()));
		}
		assertEquals(overlimitCounties.size(), 38);
	}
	
	@Test
	public void canWeInsureNewLocation(){
		List<Location> caLocations = this.accumulationEngine.filterLocationsByState(locations, "CA");
		Location alamedaLocation = new Location("US", "CA", "ALAMEDA COUNTY", "", "", 0, 0, 3000000);
		Location marinLocation = new Location("US", "CA", "MARIN COUNTY", "", "", 0, 0, 12000000);
		Location eldoradoLocation = new Location("US", "CA", "EL DORADO COUNTY", "", "", 0, 0, 1000000);
		double countyLimit = 100000000;
		assertFalse(this.accumulationEngine.canInsureNewLocation(caLocations, countyLimit, alamedaLocation));
		assertFalse(this.accumulationEngine.canInsureNewLocation(caLocations, countyLimit, marinLocation));
		assertTrue(this.accumulationEngine.canInsureNewLocation(caLocations, countyLimit, eldoradoLocation));
	}
}
