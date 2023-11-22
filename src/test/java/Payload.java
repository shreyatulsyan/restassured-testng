import org.testng.annotations.DataProvider;

public class Payload {

    public static String createUser(String name, String job){
        return "{\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"job\": \""+job+"\"\n" +
                "}";
    }


}
