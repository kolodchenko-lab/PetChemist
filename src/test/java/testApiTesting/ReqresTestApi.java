package testApiTesting;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import java.util.List;


import static io.restassured.RestAssured.given;

@Slf4j
public class ReqresTestApi {

    public static final String Base_URI = "https://reqres.in/api" ;

    @Test
    public void checkGetAllUsersAndPoint() {

        log.info("Start : getAllUsers {}");
        given()
                .baseUri(Base_URI)
                .when()
                .get("/users")
                .then()
                .statusCode(HttpStatus.SC_OK);
        log.info("END : getAllUsers");
    }
    @Test
    public void checkGetAllUsersAndPointWithResponseVariable(){
                log.info("Start : getAllUsers {}");
        Response response = given()
                .baseUri(Base_URI)
                .when()
                .get("/users");
        response.then().statusCode(200);
        response.then().body(Matchers.not(Matchers.empty()));
        response.then().header("Content-Type","application/json; charset=utf-8");
        response.then().statusLine("HTTP/1.1 200 OK");
        response.prettyPrint();
    }

    @Test
    public void checkGetAllUsersList(){
        log.info("Start : getAllUsers {}");
        Response response = given()
                .baseUri(Base_URI)
                .when()
                .get("/users");
        List<Object> listUsersFirstPage = response.jsonPath().getList("data");
        Assertions.assertThat(listUsersFirstPage.size()).isEqualTo(6);
        String firstUserLastName = response.jsonPath().getString("data[0].last_name");
        Assertions.assertThat(firstUserLastName).isEqualTo("Bluth");
        log.info("END : getAllUsers");


    }
    @JsonIgnoreProperties("createdAt")
    @Test
    public void checkCreateNewUser(){
        String randomEmail = RandomString.make(5) + "@test.com";
        String nameTest ="Jo_Jo_Test";
        Long randomIdTest = 77L;
        String lastNameTest = "Pandkovsci";
        ReqresTestClient reqresTestClient = new ReqresTestClient();
        reqresTestClient.setEmail(randomEmail);
        reqresTestClient.setFirst_name(nameTest);
        reqresTestClient.setLast_name(lastNameTest);
        reqresTestClient.setAvatar("https://reqres.in/img/faces/2-image.jpg");
        reqresTestClient.setId(randomIdTest);

        Response postResponse = given().baseUri(Base_URI)
                .when()
                .header("Content-type", ContentType.JSON)
                .body(reqresTestClient)
                .post("/users");

        postResponse.prettyPrint();
        postResponse.then().statusCode(201);


        postResponse.then().body("id",Matchers.equalTo(77));

        Assertions.assertThat(reqresTestClient.getFirst_name().equals(nameTest));


    }
}



