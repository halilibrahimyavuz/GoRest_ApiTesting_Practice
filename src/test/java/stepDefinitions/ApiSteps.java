package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import pages.ApiValidation;

public class ApiSteps extends ApiValidation {

    @Given("User given api url {string}")
    public void userGivenApiUrl(String url) {

        RestAssured.baseURI=url;

    }

    @Given("User set api endpoint {string}")
    public void userSetApiEndpoint(String endpoint) {
        RestAssured.basePath=endpoint;

    }

    @And("User create new user with request body {string} , {string} ,{string} ,{string}")
    public void userCreateNewUserWithRequestBody(String name, String gender, String email, String status) {
       response = postMethod(name ,gender,email,status);
       response.prettyPrint();
     }


    @Then("Validate the status code {int}")
    public void validateTheStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();

        try{
            Assert.assertEquals(expectedStatusCode ,actualStatusCode);
        }catch (AssertionFailedError ae){
            ae.printStackTrace();
        }

        System.out.println("Assertion  Status Code Successfull" + actualStatusCode);
    }


    @And("Validate the userId is not null")
    public void validateTheUserIdIsNotNull() {
        int id= response.jsonPath().getInt("id");
        System.out.println("id : "+ id);
        Assert.assertTrue( "id is empty",id != 0 );
        System.out.println("Assertion UserId Not Null Succesfully" );
    }

    @And("Validate the user name is {string}")
    public void validateTheUserNameIs(String expectedName) {
        String actualName =response.jsonPath().getString("name");
        System.out.println("actualName = " + actualName);
        Assert.assertEquals(actualName,expectedName);
        System.out.println("Assertion  user name is Succesfully");

    }

    @And("Validate the user gender is {string}")
    public void validateTheUserGenderIs(String expectedGender) {
        String actualGender =response.jsonPath().getString("gender");
        System.out.println("actualGender = " + actualGender);
        Assert.assertEquals(actualGender,expectedGender);
        System.out.println("Assertion  user gender is Succesfully");
    }

    @And("Validate the user email is {string}")
    public void validateTheUserEmailIs(String expectedEmail) {
        String actualEmail =response.jsonPath().getString("email");
        System.out.println("actualEmail = " + actualEmail);
        Assert.assertEquals(actualEmail,expectedEmail);
        System.out.println("Assertion  user email is Succesfully");


    }

    @And("Validate the user status is {string}")
    public void validateTheUserStatusIs(String expectedStatus) {
        String actualStatus =response.jsonPath().getString("status");
        System.out.println("actualStatus = " + actualStatus);
        Assert.assertEquals(actualStatus , expectedStatus);
        System.out.println("Assertion  user status is Succesfully");
    }
}
