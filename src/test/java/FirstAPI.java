import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Test;
import pojos.UserPojos;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
        List<UserPojos> users =  given().
                spec(requestSpec)
                .when().get()
                .then().spec(responseSpec)
                .extract().jsonPath().getList("data", UserPojos.class);

        assertThat(users.get(0).getEmail()).isEqualTo("george.bluth@reqres.in");
    }

    @Test
    public  void getUserSecond(){
        List<UserPojos> users = given().
                spec(requestSpec)
                .queryParam("page", "2")
                .when().get()
                .then().spec(responseSpec)
                .extract().jsonPath().getList("data", UserPojos.class);

        assertThat(users.get(0).getEmail()).isEqualTo("michael.lawson@reqres.in");
        assertThat(users.get(0).getFirst_name()).isEqualTo("Michael");
        assertThat(users.get(0).getLast_name()).isEqualTo("Lawson");
    }
}