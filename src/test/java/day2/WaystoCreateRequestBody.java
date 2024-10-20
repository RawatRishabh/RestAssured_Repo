package day2;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/*
Different ways to create request body
1) request body creation using Hashmap
2) request body creation using Org.JSON
3) request body creation using POJO class
4) request body creation using external json file data
 */

public class WaystoCreateRequestBody
{
    // request body creation using Hashmap
    //  Prefer to small set of data
    String id;
    //@Test(priority = 1)
    void testPostusingHashMap()
    {
        HashMap data = new HashMap();

        data.put("name","Scott");
        data.put("location","France");
        data.put("phone","123457");
        String[] courseArr= {"C","C++"};
        data.put("courses",courseArr);

       id = given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name",equalTo("Scott"))
                .body("location",equalTo("France"))
                .body("phone",equalTo("123457"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("Content-Type","application/json")
                .log().all()
               .extract()
               .path("id");

    }
    @Test(priority = 2)
    void testDelete()
    {
        given()
                .when()
                .delete("http://localhost:3000/students/"+id)
                .then()
                .statusCode(200);
    }

    // request body creation using Org.JSON
  //  @Test(priority = 1)
    void testPostusingJsonLibrary()
    {
        JSONObject data = new JSONObject();
        data.put("name","Scott");
        data.put("location","France");
        data.put("phone","123457");
        String[] courseArr= {"C","C++"};
        data.put("courses",courseArr);

        id = given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name",equalTo("Scott"))
                .body("location",equalTo("France"))
                .body("phone",equalTo("123457"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("Content-Type","application/json")
                .log().all()
                .extract()
                .path("id");

    }

   // request body creation using POJO class

    //@Test(priority = 1)
    void testPostusingPOJO()
    {
        Pojo_PostReequest data = new Pojo_PostReequest();
        data.setName("Scott");
        data.setLocation("France");
        data.setPhone("123457");
        String[] course = {"C","C++"};
        data.setCourses(course);

        id = given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name",equalTo("Scott"))
                .body("location",equalTo("France"))
                .body("phone",equalTo("123457"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("Content-Type","application/json")
                .log().all()
                .extract()
                .path("id");

    }

    // request body creation using external json file data
    @Test(priority = 1)
    void testPostusingExternalJson() throws FileNotFoundException {
        File f = new File(".\\body.json"); // get file
        FileReader fr = new FileReader(f); // file open
        JSONTokener jt = new JSONTokener(fr); // pass to tokener
        JSONObject data = new JSONObject(jt); // convert to json object
        id = given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name",equalTo("Scott"))
                .body("location",equalTo("France"))
                .body("phone",equalTo("123457"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("Content-Type","application/json")
                .log().all()
                .extract()
                .path("id");

    }


}
