package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest {

    @BeforeClass
    public void inIt()
    {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        RestAssured.basePath = "/users" ;

    }

    static String name = "Ashutosh Rana";
    static String gender = "male";
    static String status = "active";
    static String email = TestUtils.getRandomValue() + "ashutosh_rana@gmail.com";
    static long id = 5914078;
    static String bearerToken = "5ccd79c4b626c0178c68155757d8e72e7172d4baf3380a31e4e23632db81a6a0";

    @Test
    public void test001(){
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        userPojo.setEmail(email);

        Response response = given()
                .header("Authorization" ,"Bearer" + bearerToken)
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .post();
        response.prettyPrint();
        response.then().statusCode(401);
    }

    @Test
    public void getUser()
    {
        Response response =
                given().log().all()
                        .when()
                        .get();
        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test
    public void sigleUser()
    {
        Response response =
                given().log().all()
                        .when()
                        .get("/" +id);
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void verifyUserUpdateSuccessfully()
    {
        UserPojo userPojo = new UserPojo();
        userPojo.setGender("female");
        userPojo.setStatus(status);


        Response response =
                given().log().all()
                        .header("Authorization" ,"Bearer" + bearerToken)
                        .contentType(ContentType.JSON)
                        .when()
                        .body(userPojo)
                        .put("/" +id);

        response.prettyPrint();
        response.then().statusCode(404);
    }
    @Test
    public void verifyUserDeleteSuccessfully()
    {
        Response response =
                given().log().all()
                        .header("Authorization" ,"Bearer" + bearerToken)
                        .contentType(ContentType.JSON)
                        .when()
                        .delete("/" +id);

        response.prettyPrint();
        response.then().statusCode(404);
    }
}

