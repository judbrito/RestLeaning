package rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserJasonTest {

	@BeforeClass
	public static void setup() {
		// Desabilitar a verificação do certificado SSL
		RestAssured.useRelaxedHTTPSValidation();
	}

	@Test
	public void deveVerificarPrimeiroNivel() {
		given().when().get("https://restapi.wcaquino.me/users/1").then().statusCode(200).body("id", is(1))
				.body("name", containsString("Silva")).body("age", greaterThan(18));
	}

	@Test
	public void deveVerificarPrimeiroNivelDeOutrasFormas() {
		Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/users/1");

		// Path
		Assert.assertEquals(new Integer(1), response.path("id"));
		Assert.assertEquals(new Integer(1), response.path("%s", "id"));

		// Jsonpath
		JsonPath jpath = new JsonPath(response.asString());
		Assert.assertEquals(1, jpath.getInt("id"));

		// From
		int id = JsonPath.from(response.asString()).getInt("id");
		Assert.assertEquals(id, 1);
	}

	@Test
	public void deveVerificarSegundoNivel() {
		given().when().get("https://restapi.wcaquino.me/users/2").then().statusCode(200)
				.body("name", containsString("Joaquina")).body("endereco.rua", is("Rua dos bobos"));
	}

}
