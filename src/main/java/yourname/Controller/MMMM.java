package yourname.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import yourname.Entity.Student;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class MMMM {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        String jsonStr = Files.readString(Path.of("src/main/java/yourname/Controller/user.json"), StandardCharsets.US_ASCII);

        Student testing = toObject(jsonStr, Student.class);

        System.out.println(testing.getId());

        System.out.println(jsonStr);
        JsonNode json = toJsonNode(jsonStr);
        System.out.println(json);


    }



    public static <T> T toObject(String str, Class<T> clazz) {
        try {
            return mapper.readValue(str, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static JsonNode toJsonNode(String json) {
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
