package day3;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class HeadersDemo
{
   @Test(priority = 1)
    void test_headers()
    {
        // and() uses to separate multiple validation in then() (optional to use)
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .header("Content-Type","text/html; charset=ISO-8859-1")
                .and()
                .header("Content-Encoding","gzip")
                .and()
                .header("Server","gws")
                .log().headers(); // returns all headers

    }

   // @Test(priority = 2)
    void info_headers()
    {
       Response res = given()
                .when()
                .get("https://www.google.com/");

       // get single header info
        // String headerValue = res.getHeader("Content-Type");
        // System.out.println("Content-type header is = "+headerValue);

        // get all header info
        Headers headerValues = res.getHeaders();
        for(Header hd: headerValues)
        {
            System.out.println(hd.getName()+" = "+hd.getValue());
        }
    }

}
