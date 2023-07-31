package rest;

import static io.restassured.RestAssured.given;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;

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
		Assert.assertThat("Maria", Matchers.is("Maria"));
		Assert.assertThat(128, Matchers.is(128));
		Assert.assertThat(128, Matchers.isA(Integer.class));
		Assert.assertThat(128d, Matchers.greaterThan(120d));
		Assert.assertThat(128d, Matchers.lessThan(130d));

		List<Integer> impares = asList(1, 3, 5, 7, 9);
		Assert.assertThat(impares, hasSize(5));
		Assert.assertThat(impares, contains(1, 3, 5, 7, 9));
		Assert.assertThat(impares, containsInAnyOrder(1, 3, 5, 9, 7));
		Assert.assertThat(impares, hasItem(1));
		Assert.assertThat(impares, hasItems(1, 5));
		Assert.assertThat("Maria", is(not("João")));
		Assert.assertThat("Maria", not("João"));
		Assert.assertThat("Joaquina", anyOf(is("Maria"), is("Joaquina")));
		Assert.assertThat("joaquina", allOf(startsWith("joa"), endsWith("ina"), containsString("qui")));
	}

	@Test
	public void devoValidarBody() {
		given().when().get("http://restapi.wcaquino.me/ola").then().statusCode(200).body(is("Ola Mundo!"))
				.body(containsString("Ola"));
	}
}
