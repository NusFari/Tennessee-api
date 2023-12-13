package api_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
public class GetTestWithQueryParameter {
    private static final Logger LOGGER= LogManager.getLogger( GetTestWithQueryParameter.class);
    @Test
    public void etTestWithQueryParameter(){
        LOGGER.info("------------API Test:Get ALL Users with Query Parameter");

        RestAssured.baseURI = "https://reqres.in/api/users";
        //Specify the base URL or endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";
        //Get the RequestSpecification of the request that you want to send to the server.
        //The Server is specified by the BaseUrl that we have specified in the above.

        RequestSpecification httpRequest=RestAssured.given();
        //Make a request to the server by specifying the method type.
        //This will return the response from the server and store the response is a variable.
        Response response = (Response) httpRequest.queryParam("page","2").request(Method.GET);
        LOGGER.debug(response.getBody().asPrettyString());
        //Assert that the correct status is returned.

        Assert.assertEquals(response.getStatusCode(),200);
        JsonPath jsonPath =response.jsonPath();
        List<String> list=jsonPath.get("data.email");
        String emailId= "michael.lawson@reqres.in";


        boolean emailExist=list.contains(emailId);
        Assert.assertTrue(emailExist,emailId+"does not exist");
        LOGGER.info("------------End Test:Get ALL Users with Query Parameter");





    }

}


