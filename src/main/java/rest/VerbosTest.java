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

	@Test
	public void devoAlterarUsuario() {
		given().contentType(ContentType.JSON).body("{\"name\":\"Usuario alterado\",\"age\":80}").log().all().when()
				.put("https://restapi.wcaquino.me/users/1").then().log().all().statusCode(200).body("id", is(1))
				.body("name", is("Usuario alterado")).body("age", is(80)).body("salary", is(1234.5678f));
	}

	@Test
	public void devoCostumizarURL() {
		given().contentType(ContentType.JSON).body("{\"name\":\"Usuario alterado\",\"age\":80}").log().all().when()
				.put("https://restapi.wcaquino.me/{entidades}/{userId}", "users", "1").then().log().all()
				.statusCode(200).body("id", is(1)).body("name", is("Usuario alterado")).body("age", is(80))
				.body("salary", is(1234.5678f));
	}

	@Test
	public void devoCostumizarURLDois() {
		given().pathParam("entidades", "users").pathParam("userId", "1").contentType(ContentType.JSON)
				.body("{\"name\":\"Usuario alterado\",\"age\":80}").log().all().when()
				.put("https://restapi.wcaquino.me/{entidades}/{userId}", "users", "1").then().log().all()
				.statusCode(200).body("id", is(1)).body("name", is("Usuario alterado")).body("age", is(80))
				.body("salary", is(1234.5678f));
	}
}