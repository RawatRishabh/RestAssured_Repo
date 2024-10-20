package day5;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class FileUploadAndDownload
{
    //@Test(priority = 1)
    void singleFileUpload()
    {
        File myfile = new File("C:\\Users\\Rishabh Rawat\\OneDrive\\Documents\\TestData\\text1.txt");
        given()
                .multiPart("file",myfile) // upload the file
                .contentType("multipart/form-data")
                .when()
            .post("http://localhost:8080/uploadFile")
            .then()
            .statusCode(200)
            .body("fileName",equalTo("text1.txt"))
            .log().all();
    }

   // @Test(priority = 2)
    void multipleFileUpload1()
    {
        File myfile1 = new File("C:\\Users\\Rishabh Rawat\\OneDrive\\Documents\\TestData\\text1.txt");
        File myfile2 = new File("C:\\Users\\Rishabh Rawat\\OneDrive\\Documents\\TestData\\text2.txt");

        given()
                .multiPart("files",myfile1) // upload the file
                .multiPart("files",myfile2)
                .contentType("multipart/form-data")
                .when()
                .post("http://localhost:8080/uploadMultipleFiles")
                .then()
                .statusCode(200)
                .body("[0].fileName",equalTo("text1.txt"))
                .body("[1].fileName",equalTo("text2.txt"))
                .log().all();
    }
    // @Test(priority = 3)
    void multipleFileUpload2()  // won't work for all kinds API
    {
        File myfile1 = new File("C:\\Users\\Rishabh Rawat\\OneDrive\\Documents\\TestData\\text1.txt");
        File myfile2 = new File("C:\\Users\\Rishabh Rawat\\OneDrive\\Documents\\TestData\\text2.txt");
        File[] filearr = {myfile1,myfile2};
        given()
                .multiPart("file",filearr) // upload file
                .contentType("multipart/form-data")
                .when()
                .post("http://localhost:8080/uploadMultipleFiles")
                .then()
                .statusCode(200)
                .body("[0].fileName",equalTo("text1.txt"))
                .body("[1].fileName",equalTo("text2.txt"))
                .log().all();
    }

    @Test(priority = 4)
    void fileDownload()
    {
        given()
                .when()
                .get("http://localhost:8080/downloadFile/text1.txt")
                .then()
                .statusCode(200)
                .log().all();
    }
}
