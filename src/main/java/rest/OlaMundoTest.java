package rest;

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
}