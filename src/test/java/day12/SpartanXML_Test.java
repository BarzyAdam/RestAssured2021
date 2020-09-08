package day12;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanXML_Test {

    // boilerplate goes here
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";
    }

    @DisplayName("Test XML Response from GET /Spartans")
    @Test
    public void testSpartanXML(){

        given()
                .accept(ContentType.XML).
        when()
                .get("/spartans").
        then()
                .contentType(ContentType.XML)
                .body("List.item[0].name", is("Mina") )
                .body("List.item[0].gender", is("Male"))
                .body("List.item[0].id", is("424"))

        ;
        // XMLPath xp = response.xmlPath();


    }
    @DisplayName("XMLPath object to extract the data out")
    @Test
    public void testSpartanExtractData(){

        Response response = given()
                .accept(ContentType.XML).
                        when()
                .get("/spartans") ;
        // getting XMLPath object just like we did for JsonPath object
        XmlPath xp = response.xmlPath();

        // get the first spartan id and store it into the int
        int firstID = xp.getInt("List.item[0].id");
        System.out.println("firstID = " + firstID);
        String firstName = xp.getString("List.item[0].name") ;
        System.out.println("firstName = " + firstName);
        long firstPhone = xp.getLong("List.item[0].phone");
        System.out.println("firstPhone = " + firstPhone);



    }



}