package albums;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class jsonplaceholderPUTPATCHTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String ALBUMS = "albums";
    private final String CONTENT_TYPE = "application/json";
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
    public void jsonplaceholderUpdateAlbumPUTTest() {
        JSONObject album = new JSONObject();
        album.put("title", fakeTitle);
        album.put("userId", 2);

        Response response = given()
                .pathParam("id", 1)
                .contentType(CONTENT_TYPE)
                .body(album.toString())
                .when()
                .put(BASE_URL + "/" + ALBUMS + "/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        JsonPath json = response.jsonPath();
        assertEquals(fakeTitle, json.get("title"));
        assertEquals(2, (Integer) json.get("userId"));

    }

    @Test
    public void jsonplaceholderUpdateAlbumPATCHTest(){
        JSONObject albumDetails = new JSONObject();
        albumDetails.put("title", fakeTitle);

        Response response = given()
                .pathParam("id", 1)
                .contentType(CONTENT_TYPE)
                .body(albumDetails.toString())
                .when()
                .patch(BASE_URL + "/" + ALBUMS + "/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        JsonPath json = response.jsonPath();
        assertEquals(fakeTitle, json.get("title"));
    }
}
