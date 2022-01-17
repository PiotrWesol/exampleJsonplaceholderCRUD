package users;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Collectors;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonplaceholderGETTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String USERS = "users";

    @Test
    public void jsonplaceholderReadAllUsersEmailsEndingOnPl() {
        Response response = given()
                .when()
                .get(BASE_URL + "/" + USERS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        JsonPath json = response.jsonPath();
        List<String> emails = json.get("email");

        List<String> countsEmailsEndingOnPl = emails.stream()
                .filter(email -> email.endsWith(".pl"))
                .collect(Collectors.toList());

        assertEquals(0, countsEmailsEndingOnPl.size());

    }
}
