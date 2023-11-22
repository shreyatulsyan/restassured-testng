import io.restassured.path.json.JsonPath;

public class ReusableMethods {

    public static JsonPath stringToJson(String str){
        JsonPath js =  new JsonPath(str);
        return js;
    }
}
