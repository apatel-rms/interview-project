package interviewProject;

import org.json.simple.JSONObject;

public class Location {
	
	private static final String countryCodeTag = "CountryCode";
	private static final String stateCodeTag = "Admin1Code";
	private static final String countyNameTag = "Admin2Name";
	private static final String postalCodeTag = "PostalCode";
	private static final String streetAddressTag = "StreetAddress";
	private static final String latitudeTag = "Latitude";
	private static final String longitudeTag = "Longitude";
	private static final String valueTag = "Value";
	
	private String countryCode;
	private String stateCode;
	private String countyName;
	private String postalCode;
	private String streetAddress;
	private double latitude;
	private double longitude;
	private double value;
	
	public Location(String countryCode, String stateCode, String countyName, 
			String postalCode, String streetAddress, double latitude, double longitude, double value){
		this.countryCode = countryCode;
		this.stateCode = stateCode;
		this.countyName = countyName;
		this.postalCode = postalCode;
		this.streetAddress = streetAddress;
		this.latitude = latitude;
		this.longitude = longitude;
		this.value = value;
	}
	
	public Location(JSONObject locationObject){
		Object o;
		if(locationObject.containsKey(countryCodeTag)){
			o = locationObject.get(countryCodeTag);
			if(o != null)
				this.countryCode = o.toString().trim();
		}
		if(locationObject.containsKey(stateCodeTag)){
			o = locationObject.get(stateCodeTag);
			if(o != null)
				this.stateCode = o.toString().trim();
		}
		if(locationObject.containsKey(countyNameTag)){
			o = locationObject.get(countyNameTag);
			if(o != null)
				this.countyName = o.toString().trim();
		}
		if(locationObject.containsKey(postalCodeTag)){
			o = locationObject.get(postalCodeTag);
			if(o != null)
				this.postalCode = o.toString().trim();
		}
		if(locationObject.containsKey(streetAddressTag)){
			o = locationObject.get(streetAddressTag);
			if(o != null)
				this.streetAddress = o.toString().trim();
		}
		if(locationObject.containsKey(latitudeTag)){
			o = locationObject.get(latitudeTag);
			if(o != null)
				this.latitude = Double.parseDouble(o.toString().trim());
		}
		if(locationObject.containsKey(longitudeTag)){
			o = locationObject.get(longitudeTag);
			if(o != null)
				this.longitude = Double.parseDouble(o.toString().trim());
		}
		if(locationObject.containsKey(valueTag)){
			o = locationObject.get(valueTag);
			if(o != null)
				this.value = Double.parseDouble(o.toString().trim());
		}
	}
	
	public String getStateCode(){
		return this.stateCode;
	}
	
	public double getValue(){
		return this.value;
	}

	public String getCountyName() {
		return this.countyName;
	}

	public String getPostalCode() {
		return this.postalCode;
	}
	
	public String getCountryCode() {
		return this.countryCode;
	}
	
	public double getLatitude() {
		return this.latitude;
	}
	
	public double getLongitude() {
		return this.longitude;
	}
	
	public String getStreetAddress() {
		return this.streetAddress;
	}
}
