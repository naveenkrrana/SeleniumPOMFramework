package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class ApiConfig {

    public static RequestSpecification getBaseSpec() {
        // Pull the API auth token from config so it isn't hardcoded in the script
        String bearerToken = ConfigReader.getProperty("api_token");

        return new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                // Pass the standard Bearer token format required by secure endpoints
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();
    }
}