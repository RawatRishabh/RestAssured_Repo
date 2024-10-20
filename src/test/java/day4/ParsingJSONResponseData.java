package day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ParsingJSONResponseData
{
    //@Test(priority = 1)
    void testJsonResponse()
    {
        // Approach 1

      /*  given()
                .contentType(ContentType.JSON) // type of data we are sending with request
                .when()
                .get("http://localhost:3000/store")
                .then()
                .statusCode(200)
                .header("Content-Type","application/json")
                .body("book[3].title",equalTo("The lord of the rings")); */

        // Approach 2

        Response res = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/store"); // return the response
        Assert.assertEquals(res.getStatusCode(),200); // validation 1
        Assert.assertEquals(res.getHeader("Content-Type"),"application/json"); // validation 2
        String bookName = res.jsonPath().get("book[3].title").toString(); // .jsonPath().get(jsonpath) return object then convert to string
        Assert.assertEquals(bookName,"The lord of the rings"); // validation 3

    }

    @Test(priority = 2)
    void testJsonResponseData()
    {

        Response res = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/store"); // return the response

        // JSONObject Class
        JSONObject jo = new JSONObject(res.asString()); // converting response to JSON object type
        /*for(int i=0;i<jo.getJSONArray("book").length();i++)
        {
           String bookTitle =  jo.getJSONArray("book").getJSONObject(i).get("title").toString();
           System.out.println(bookTitle);
        }*/
        // Search for title of the book in json - validation 1
        boolean status = false;
        for(int i=0;i<jo.getJSONArray("book").length();i++)
        {
           String bookTitle =  jo.getJSONArray("book").getJSONObject(i).get("title").toString();
           if(bookTitle.equals("The lord of the rings"))
           {
                status = true;
                break;
           }
        }
        Assert.assertTrue(status,"true");

        // validate 2 , total price of books
        double price = 0.0;
        for(int i=0;i<jo.getJSONArray("book").length();i++)
        {
            String bookTitle =  jo.getJSONArray("book").getJSONObject(i).get("price").toString();
            price = price + Double.parseDouble(bookTitle);
        }
        System.out.println("Total price : "+price);
        Assert.assertEquals(price,526.0);
    }


}
