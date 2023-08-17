package rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class AuthTest {
	//endereço endpoint atualizada!
@Test
public void deveAcessarSWAPI() {
given()
.log().all()
.when()
.get("https://swapi.dev/api/people/1")
.then()
.log().all()
.statusCode(200)
.body("name", is("Luke Skywalker"))
;
}
}
