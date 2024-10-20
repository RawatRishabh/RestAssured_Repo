package day5;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ParsingXMLResponse
{
    //@Test(priority = 1)
    void testXMLResponse()
    {
        // Approach 1
       /* given()
                .when()
                .get("https://thetestrequest.com/authors.xml")
                .then()
                .statusCode(200)
                .header("Content-Type","application/xml; charset=utf-8")
                .body("objects.object[1].name",equalTo("Jeffie Wolf I"));*/

        // Approach 2
        Response res = given()
                .when()
                .get("https://thetestrequest.com/authors.xml");

        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.getHeader("Content-Type"),"application/xml; charset=utf-8");
        String email = res.xmlPath().get("objects.object[1].name").toString(); // conversion is mandatory due to compare not possible b/w objects
        Assert.assertEquals(email,"Jeffie Wolf I");

    }
    @Test(priority = 2)
    void testXMLResponseBody()
    {
        Response res = given()
                .when()
                .get("https://thetestrequest.com/authors.xml");
        // toString(), convert a data to string format
        // asString(), convert a response to string format
        // XmlPath class
        XmlPath xmlObj = new XmlPath(res.asString()); // convert response type to string type

        // Verify total number of object in objects
        List<String> objList = xmlObj.getList("Objects.object");
        Assert.assertEquals(objList.size(),5);

        // Verify object name present in response
        Boolean check = false;
        List<String> objName = xmlObj.getList("Objects.object.name");
        for(String name : objName)
        {
            //System.out.println(name);
            if(name.equals("Gracia Keeling"))
            {
                check = true;
                break;
            }
        }
        Assert.assertEquals(check,true);

    }

}
