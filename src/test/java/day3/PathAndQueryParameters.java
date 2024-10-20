package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParameters
{
    // https://reqres.in/api/users?page=2%id=5
    // For multiple path parameter and query parameter we need to specify the multiple pathparam() and queryparam() methods
    @Test
    void testQueryandParamters()
    {
        // try to separate the path and query para from url and written in given()
        given()
                .pathParam("mypath1","api") // routing to data
                .pathParam("mypath2","users") // path parameters
                .queryParam("page",2) // query parameters (it will go along with request automatically)
                .queryParam("id",5) // filter the data
                .when()
                .get("https://reqres.in/{mypath1}/{mypath2}") // only path parameter is using here
                .then()
                .statusCode(200)
                .log().all();
    }

}
