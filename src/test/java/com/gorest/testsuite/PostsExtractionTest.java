package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        response = given()
                .when()
                .get("/public/v2/posts?page=1&per_page=25")
                .then().statusCode(200);

    }

    // 1. Extract the title
    @Test
    public void test001(){
        List<String> titles = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title are :" + titles);
        System.out.println("------------------End of Test---------------------------");
    }
    // 2. Extract the total number of record
    @Test
    public void test002(){
        int numberOfRecords = response.extract().path("size()");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the total number of record are :" + numberOfRecords);
        System.out.println("------------------End of Test---------------------------");
    }
    // 3. Extract the body of 15th record
    @Test
    public void test003(){
        String body = response.extract().path("[14].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The the body of 15th record is :" + body);
        System.out.println("------------------End of Test---------------------------");
    }
    // 4. Extract the user_id of all the records
    @Test
    public void test004(){
        List<Integer> user_id = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The user_id of all the records are :" + user_id);
        System.out.println("------------------End of Test---------------------------");
    }
    // 5. Extract the title of all the records
    @Test
    public void test005(){
        List<String> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all the records are :" + title);
        System.out.println("------------------End of Test---------------------------");
    }
    // 6. Extract the title of all records whose user_id = 5914200
    @Test
    public void test006(){
        List<String> title = response.extract().path("findAll{it.user_id == 5914206}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all records whose user_id = 5914206 are :" + title);
        System.out.println("------------------End of Test---------------------------");
    }
    // 7. Extract the body of all records whose id = 93957
    @Test
    public void test007(){
        List<String> body = response.extract().path("findAll{it.id == 93967}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of all records whose id = 93967 are :" + body);
        System.out.println("------------------End of Test---------------------------");
    }
}
