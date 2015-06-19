package interviewProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccumulationEngine {

	public List<Location> filterLocationsByState(List<Location> locations, String stateCode){
		List<Location> filteredLocations = new ArrayList<Location>();
		for (Location location : locations) {
			String locationStateCode = location.getStateCode();
			if(locationStateCode != null && 
					locationStateCode.equalsIgnoreCase(stateCode)){
				filteredLocations.add(location);
			}
		}
		return filteredLocations;
	}
	
	public List<Location> filterLocationsByCounty(List<Location> locations, String stateCode, String countyName){
		List<Location> filteredLocations = new ArrayList<Location>();
		for (Location location : locations) {
			String locationStateCode = location.getStateCode();
			String locationCountyName = location.getCountyName();
			if(locationStateCode != null && locationCountyName != null &&
					locationStateCode.equalsIgnoreCase(stateCode) &&
					locationCountyName.equalsIgnoreCase(countyName)){
				filteredLocations.add(location);
			}
		}
		return filteredLocations;
	}
	
	public List<Location> filterLocationsByPostalCode(List<Location> locations, String postalCode){
		List<Location> filteredLocations = new ArrayList<Location>();
		for (Location location : locations) {
			String locationPostalCode = location.getPostalCode();
			if(locationPostalCode != null &&
					locationPostalCode.equalsIgnoreCase(postalCode)){
				filteredLocations.add(location);
			}
		}
		return filteredLocations;
	}
	
	public double accumulateOverState(List<Location> locations, String stateCode) {
		List<Location> filteredLocations = filterLocationsByState(locations, stateCode); 
		double totalValue = 0;
		
		for (Location location : filteredLocations) {
			totalValue += location.getValue();
		}
		return totalValue;
	}

	public double accumulationOverCounty(List<Location> locations,
			String stateCode, String countyName) {
		List<Location> filteredLocations = filterLocationsByCounty(locations, stateCode, countyName);
		double totalValue = 0;
		for (Location location : filteredLocations) {
			totalValue += location.getValue();
		}
		return totalValue;
	}

	public Map<String, Double> exposureByCounty(List<Location> locations) {
		Map<String, Double> countyExposures = new HashMap<String, Double>();
		for (Location location : locations) {
			if(countyExposures.containsKey(location.getCountyName())){
				countyExposures.put(location.getCountyName(), countyExposures.get(location.getCountyName()) + location.getValue());
			}
			else{
				countyExposures.put(location.getCountyName(), location.getValue());
			}
		}
		return countyExposures;
	}
	
	public boolean canInsureNewLocation(List<Location> locations, double countyLimit, Location newLocation){
		Map<String, Double> countyExposures = exposureByCounty(locations);
		if(countyExposures.containsKey(newLocation.getCountyName())){
			double currentCountyExposure = countyExposures.get(newLocation.getCountyName());
			double proposedCountyExposure = currentCountyExposure + newLocation.getValue();
			System.out.println(String.format("Current County Exposure:%f, Proposed Exposure Value:%f, Total Proposed County Exposure:%f"
					,currentCountyExposure, newLocation.getValue(), proposedCountyExposure));
			if(proposedCountyExposure > countyLimit){
				return false;
			}else{
				return true;
			}
		}
		return false;
	}
}
