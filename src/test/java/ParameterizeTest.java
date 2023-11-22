import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParameterizeTest {

    @Test
    public void registerUser(){
        RestAssured.baseURI=("https://reqres.in/");
        RestAssured.basePath = "/api";
        String res = given().log().all().
                body(Payload.createUser("morpheus","leader")).
                when().post("/users").
                then().assertThat().statusCode(201).extract().asString();

        System.out.println(res);

    }
    @Test(dataProvider="usersData")
    public void registerMultipleUser(String name, String job){
        RestAssured.baseURI=("https://reqres.in/");
        RestAssured.basePath = "/api";
        String res = given().log().all().
                body(Payload.createUser(name,job)).
                when().post("/users").
                then().assertThat().statusCode(201).extract().asString();

        System.out.println(res);

    }
    @DataProvider(name="usersData")
    public Object[][] users() {
        return new Object[][] {
                {"shreya","QA"},
                {"Ayushi","QA"}
        };
    };
}
