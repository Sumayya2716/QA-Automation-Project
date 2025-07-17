package POJO_Class;

import java.util.List;

public class Serailization_AddPlace {
	
	private Serailization_SubLocation location;
	private String name;
	private String phone_number;
	private String address;
	private List<String> types;
	private String website;
	private String language;
	
	public void setLocation(Serailization_SubLocation location) {
		this.location = location;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	private int accuracy;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Serailization_SubLocation getLocation() {
		return location;
	}
	public int getAccuracy() {
		return accuracy;
	}
	
	
	
	




}
