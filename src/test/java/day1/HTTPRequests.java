package day1;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class HTTPRequests
{
    int id;
   @Test(priority = 1)
    void getUser()
    {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
        .then()
                .statusCode(200)
                .body("page",equalTo(2))
                .log().all(); // display the response payload on console
    }
    @Test(priority = 2)
    void createUser()
    {
        HashMap data = new HashMap();
        data.put("name","Rishabh");
        data.put("job","QA");

       id = given()
                .contentType("application/json")
                .body(data)
               .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");

    }
    @Test(priority = 3,dependsOnMethods = {"createUser"})
    void updateUser()
    {
        HashMap data = new HashMap();
        data.put("name","Rishu");
        data.put("job","SDET");

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .put("https://reqres.in/api/users/"+id)
                .then()
                .statusCode(200)
                .log().all();

    }
    @Test(priority = 4,dependsOnMethods = {"createUser"})
    void deleteUser()
    {
        given()
                .when()
                .delete("https://reqres.in/api/users/"+id)
                .then()
                .statusCode(204)
                .log().all();
    }


}
