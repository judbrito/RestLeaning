package rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;

import io.restassured.http.ContentType;

public class VerbosTest {
	// @Test
	public void devoSalvarUsuario() {
		given().log().all().contentType("application/json").body("{\"name\":\"jose\",\"age\":50}").when()
				.post("https://restapi.wcaquino.me/users").then().log().all().statusCode(201)
				.body("id", is(notNullValue())).body("name", is("jose")).body("age", is(50));

	}

	@Test
	public void devoSalvarUsuarioViaXml() {
		given().contentType(ContentType.XML).body("<user><name>Jose</name><age>50</age></user>").log().all().when()
				.post("https://restapi.wcaquino.me/usersXMl").then().log().all().statusCode(201)
				.body("user.@id", is(notNullValue())).body("user.name", is("Jose")).body("user.age", is("50"));
	}
}