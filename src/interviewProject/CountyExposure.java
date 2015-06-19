package interviewProject;

public class CountyExposure {
	private String countyName;
	private Double exposure;
	
	public CountyExposure(String countyName, double exposure){
		this.countyName = countyName;
		this.exposure = exposure;
	}
	
	public String getCountyName(){
		return this.countyName;
	}
	
	public double getExposure(){
		return this.exposure;
	}
	
	public void setExposure(double exposure){
		this.exposure = exposure;
	}
}
