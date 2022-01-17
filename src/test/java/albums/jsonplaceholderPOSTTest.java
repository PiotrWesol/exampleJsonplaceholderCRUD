package albums;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class jsonplaceholderPOSTTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String ALBUMS = "albums";
    private static Faker faker;
    private String fakeTitle;


    @BeforeAll
    public static void beforeAll() {
        faker = new Faker();
    }

    @BeforeEach
    public void beforeEach() {
        fakeTitle = faker.music().genre();
    }

    @Test
    public void jsonplaceholderCreateNewAlbum() {

        JSONObject album = new JSONObject();
        album.put("userId", 1);
        album.put("title", fakeTitle);

        Response response = given()
                .contentType("application/json")
                .body(album.toString())
                .when()
                .post(BASE_URL + "/" + ALBUMS)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().response();

        JsonPath json = response.jsonPath();

        Assertions.assertEquals(fakeTitle, json.get("title"));
    }
}
