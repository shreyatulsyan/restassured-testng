
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class ReqResAPI {

    @Test
    public String listUsers(){
        RestAssured.baseURI ="https://reqres.in/";
        RestAssured.basePath = "/api";
        String res = given().queryParam("page",2).
                when().
                get("/users").then().
                assertThat().statusCode(200).body("total",equalTo(12)).extract().asString();
        return res;
    }

    @Test
    public void addUsers(){
        RestAssured.baseURI ="https://reqres.in/";
        RestAssured.basePath = "/api";
        given().body("{\n" +
                "    \"name\": \"shreya\",\n" +
                "    \"job\": \"leader\"\n" +
                "}\n").
                when().log().all().
                post("/users").
                then().statusCode(201);

    }

    @Test
    public void updateUser(){
        RestAssured.baseURI ="https://reqres.in/";
        RestAssured.basePath = "/api";
        String response = given().body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}").
                when().
                put("/users/2").
                then().
                assertThat().statusCode(200).
                body("updatedAt", startsWith("2023-11-22")).
                extract().response().asString();

        System.out.println(response);

        //Reading json
        JsonPath j1 =  ReusableMethods.stringToJson(response);
        String updateddata = j1.getString("updatedAt");
        System.out.println("Updated data: " + updateddata);
    }

    @Test
    public void validate_listUsers_API(){

        JsonPath js = ReusableMethods.stringToJson(listUsers());
        System.out.println("===Print no of users");
        int noOfUsers = js.getInt("data.size()");
        System.out.println(noOfUsers);

        System.out.println("===Print total_pages");
        System.out.println( js.getInt("total_pages"));

        System.out.println("===Print email of first user");
        System.out.println( js.getString("data[0].email"));

        System.out.println("===Print all users email and first_name");

        for(int i=0;i<noOfUsers;i++){
        System.out.println( js.getString("data["+i+"].email"));
        System.out.println( js.getString("data["+i+"].first_name"));
        }

        System.out.println("===Print last_name of id 11");
        for(int j=0;j<noOfUsers;j++){
           int getID =  js.getInt("data["+j+"].id");
           if(getID==11){
               System.out.println(js.getString("data["+j+"].last_name"));
               break;
           }
        }

        System.out.println("===Verify if no of users matches with per_page");
        System.out.println(noOfUsers == (js.getInt("per_page")));

    }

}
