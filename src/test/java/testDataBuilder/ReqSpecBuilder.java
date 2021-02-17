package testDataBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class ReqSpecBuilder {
	
	String projectpath = System.getProperty("user.dir");
	public static RequestSpecification req;
	
	public RequestSpecification getRequestSpecObject() throws IOException {
		if(req==null) {
		File file = new File(projectpath+"/Logs/logs.txt");
		PrintStream stream = new PrintStream(new FileOutputStream(file));
		RestAssured.baseURI= getDatafromGlobal();
		req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").
				addFilter(RequestLoggingFilter.logRequestTo(stream)).addFilter(ResponseLoggingFilter.logResponseTo(stream)).setContentType(ContentType.JSON).build();
		return req;
		}
		return req;
	}
	
	public String getDatafromGlobal() throws IOException {
		FileInputStream fis = new FileInputStream(projectpath+"/src/test/java/testDataBuilder/global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty("baseURL");
	}
	
	public String getJsonPath(String response,String key) {
		JsonPath js = new JsonPath(response);
		return js.getString(key);
	}
	
}

