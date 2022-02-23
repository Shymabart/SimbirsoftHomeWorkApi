import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Test;
import pojos.UserPojos;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class FirstAPI {
    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in/api")
            .setBasePath("/users")
            .setContentType(ContentType.JSON)
            .build();

    private static final ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    @Test
    public  void getUserFirst(){
        given().
                spec(requestSpec)
                .when().get()
                .then().spec(responseSpec)
                .body("data[0].email",equalTo("george.bluth@reqres.in"))
                .extract().jsonPath().getList("data",UserPojos.class);
    }

    @Test
    public  void getUserSecond(){
        given().
                spec(requestSpec)
                .queryParam("page", "2")
                .when().get()
                .then().spec(responseSpec)
                .body("data[0].email",equalTo("michael.lawson@reqres.in"))
                .extract().jsonPath().getList("data",UserPojos.class);
    }
}