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

public class FirstTest {
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
        assertThat(users.get(0).getFirst_name()).isEqualTo("George");
        assertThat(users.get(0).getLast_name()).isEqualTo("Bluth");
    }

    @Test
    public  void getUserSecond(){
             given().
                    spec(requestSpec)
                    .queryParam("per_page", 12)
                    .when().get()
                    .then().spec(responseSpec)
                    .body("data.find{it.email=='michael.lawson@reqres.in'}.first_name",equalTo("Michael"))
                    .body("data.find{it.email=='michael.lawson@reqres.in'}.last_name",equalTo("Lawson"))
                    .extract().jsonPath().getList("data", UserPojos.class);
            }
    }
