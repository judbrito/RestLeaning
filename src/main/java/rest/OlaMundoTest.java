package rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundoTest {

	@Test
	public void testOlaMundo() {
		Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/ola");
		Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
		Assert.assertTrue(response.statusCode() == 200);
		Assert.assertTrue("estatus code não é  200", response.statusCode() == 200);

		System.out.println(response.getBody().asString().equals("Ola Mundo!"));
		System.out.println(response.statusCode() == 200);

		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);

	}

	@Test
	public void devoConhecerOutrasFormasRestAssured() {
		Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me:80/ola");
		Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
		given().when().get("http://restapi.wcaquino.me/ola").then().statusCode(200);
	}

	@Test
	public void exemploTresDevoConhecer() {
		RestAssured.get("http://restapi.wcaquino.me/ola").then().statusCode(200);
		System.out.println("uma linha codigo para verificar o status code");

	}

	@Test
	public void devoConhecerMatchersHamcrest() {
		Assert.assertThat("Maria", Matchers.is("MAria"));
		Assert.assertThat(128, Matchers.is(128));
		Assert.assertThat(128, Matchers.isA(Integer.class));
		Assert.assertThat(128, Matchers.isA(Double.class));
		Assert.assertThat(128d, Matchers.greaterThan(128d));
		Assert.assertThat(128d, Matchers.lessThan(120d));

		List<Integer> impares = Arrays.asList(1, 3, 5, 7, 9);
		Assert.assertThat(impares, hasSize(5));
	}
}
