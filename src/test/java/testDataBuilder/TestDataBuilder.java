package testDataBuilder;

import java.util.ArrayList;
import java.util.List;

import serialization.AddPlace;
import serialization.Location;

public class TestDataBuilder {
	
	
	public AddPlace getddPlaceBuilderObject(String name,String language,String address) {
		AddPlace ap = new AddPlace();
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setName(name);
		ap.setPhone_number("987-435-6578");
		List<String> type = new ArrayList<String>();
		type.add("shoe park");
		type.add("shop");
		ap.setTypes(type);
		ap.setWebsite("www.google.com");
		return ap;
	}
	
	public String getDeletePayload(String place_id) {
		return "{\r\n" + 
				"    \"place_id\":\""+place_id+"\"\r\n" + 
				"}";
	}
}
