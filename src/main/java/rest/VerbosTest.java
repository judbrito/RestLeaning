package rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;

public class VerbosTest {
	@Test
	public void devoSalvarUsuário() {
		given().log().all().contentType("application/json").body("{\"name\":\"jose\",\"age\":50}").when()
				.post("https://restapi.wcaquino.me/users").then().log().all().statusCode(201)
				.body("id", is(notNullValue())).body("name", is("jose")).body("age", is(50));

	}
}
