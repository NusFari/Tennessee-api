package api_test;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimplePostTest {
    private static final Logger LOGGER = LogManager.getLogger(SimplePostTest.class);

    @Test
    public void createNewUser() {
        LOGGER.info("------------API Test:Create new User");

        RestAssured.baseURI = "https://reqres.in/api/users";
        //Specify the base URL or endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";
        //Get the RequestSpecification of the request that you want to send to the server.
        //The Server is specified by the BaseUrl that we have specified in the above.

        RequestSpecification httpRequest = RestAssured.given();
        Faker faker=new Faker();
        String fullName=faker.name().fullName();
        LOGGER.debug("New User Full Name:"+fullName);

        String userRole=faker.job().title();
        LOGGER.debug("User Job:"+userRole);
        //Creating the request body

        JSONObject reqBody =new JSONObject();
        reqBody.put("name",fullName);
        reqBody.put("job",userRole);
        httpRequest.header("Content-Type","application/json");
        httpRequest.body(reqBody.toJSONString());

        //Make a request to the server by specifying the method type.
        //This will return the response from the server and store the response is a variable.
        Response response =httpRequest.request(Method.POST);

       LOGGER.debug(response.getBody().asPrettyString());
        //Assert that the correct status is returned.


        //Assert that the correct status is returned.
        Assert.assertEquals(response.getStatusCode(), 201);

        JsonPath jsonPath = response.jsonPath();
        String actualName=jsonPath.getString("name");

        Assert.assertEquals(actualName, fullName);
        LOGGER.info("------------End Test: Create new User-----------------");


    }
}