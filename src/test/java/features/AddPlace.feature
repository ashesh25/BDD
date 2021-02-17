Feature: Validating Place Api
@AddPlace
Scenario Outline: Verify if place successfully added using Add Place API
	Given AddPlace payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then the api call got success result with status code 200
	And "status" in response body is "ok"
	And Validate the "<name>" in the "GetPlaceAPI" response
	
Examples:
	|name  | language | address        |
	|Ayush | Spanish  | 212 ADE Street |
#	|Ankit | Portugues| 215 FGH Street |
@DeletePlace	
Scenario:
	Given Delete Place Payload
	When user calls "DeletePlaceAPI" with "POST" http request
	Then the api call got success result with status code 200
	And "status" in response body is "ok"		