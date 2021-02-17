package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable {
		
		stepDefination sd = new stepDefination();
		if(stepDefination.placeID==null) {
			sd.add_place_payload_with("Ash", "Bengali", "India");
			sd.user_calls_something_with_something_http_request("AddPlaceAPI", "POST");
			sd.the_api_call_got_success_result_with_status_code_200();
			sd.validate_the_in_the_response("Ash", "GetPlaceAPI");
		}
	}
		
}
