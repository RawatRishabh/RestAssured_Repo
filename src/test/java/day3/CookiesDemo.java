package day3;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CookiesDemo
{
    //@Test(priority = 1)
    void testCookies()
    {
        // cookie value always change whenever hit the request
        // cookie name do not change but value should be change
        // point is value should not be match, if not match that indicates the cookies is generated
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                // validation the cookies generation
                .cookie("AEC","AVYB7crvd0u2GznTotUDT34r0dond0g4-F5t7KM-V2v_RE7apD85jSy4FA")
                .log().all();
    }

    @Test(priority = 2)
    void getCookiesinfo()
    {

    Response res =  given()
                .when()
                .get("https://www.google.com/");

    // get single cookie info
     // String cookie_value = res.getCookie("AEC");
      // System.out.println("value of cookie is = "+cookie_value);

        // get all cookies info
        Map<String,String> cookies_values = res.getCookies();
        for(String k :cookies_values.keySet())
        {
            System.out.println("value of cookie is = "+res.getCookie(k));
        }

    }


}
