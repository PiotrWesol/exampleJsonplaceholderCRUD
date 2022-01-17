package albums;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class jsonplaceholderGETTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String ALBUMS = "albums";

    @Test
    public void jsonplacholderReadAllAlbums() {
        Response response = given()
                .when()
                .get(BASE_URL + "/" + ALBUMS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        JsonPath json = response.jsonPath();
        List<String> albums = json.getList("id");
        assertEquals(100, albums.size());
    }

    @Test
    public void jsonplaceholderReadOneAlbum() {
        Response response = given()
                .pathParam("id", 1)
                .when()
                .get(BASE_URL + "/" + ALBUMS + "/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        JsonPath json = response.jsonPath();
        assertEquals("quidem molestiae enim", json.get("title"));

    }
}
