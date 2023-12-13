package api_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleDeleteTest {
    private static final Logger LOGGER= LogManager.getLogger( SimpleDeleteTest.class);
    @Test
    public void DeleteSingleUser(){
        LOGGER.info("------------API Test:Delete Single User--------");
        RestAssured.baseURI = "https://reqres.in/api/users";
        //Specify the base URL or endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";
        //Get the RequestSpecification of the request that you want to send to the server.
        //The Server is specified by the BaseUrl that we have specified in the above.

        RequestSpecification httpRequest=RestAssured.given();
        //Make a request to the server by specifying the method type.
        //This will return the response from the server and store the response is a variable.
        String id="2";
        Response response = (Response) httpRequest.request(Method.DELETE,id);
        //Assert that the correct status is returned.
        Assert.assertEquals(response.getStatusCode(),204);

        LOGGER.info("------------End Test:Delete Single User-------");


    }
}
