package rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.XmlPath.CompatibilityMode;

public class AuthTest {
	// endereço endpoint atualizada!
	@Test
	public void deveAcessarSWAPI() {
		given().log().all().when().get("https://swapi.dev/api/people/1").then().log().all().statusCode(200).body("name",
				is("Luke Skywalker"));
	}

	@Test
	public void deveObterclima() {
		given().log().all().queryParam("q", "Fortaleza,BR").queryParam("appid", "6bfe1ceed23fa95ff2c38a8353e830de")
				.queryParam("units", "metric").when().get("https://api.openweathermap.org/data/2.5/weather").then()
				.log().all().statusCode(200).body("name", is("Fortaleza")).body("coord.lon", is(-38.5247f))
				.body("main.temp", greaterThan(25f));
	}

//https://api.openweathermap.org/data/2.5/weather?q=Fortaleza,BR&appid=6bfe1ceed23fa95ff2c38a8353e830de&units=metric
	@Test
	public void naoDeveAcessarSemSenha() {
		given().log().all().when().get("https://restapi.wcaquino.me/basicauth").then().log().all().statusCode(401);
	}

	@Test
	public void deveFazerAutenticaoBasica() {
		given().log().all().when().get("https://admin:senha@restapi.wcaquino.me/basicauth").then().log().all()
				.statusCode(200).body("status", is("logado"));
	}

	@Test
	public void deveFazerAutenticaoBasica2() {
		given().log().all().auth().basic("admin", "senha").when().get("https://restapi.wcaquino.me/basicauth").then()
				.log().all().statusCode(200).body("status", is("logado"));
	}

	@Test
	public void deveFazerAutenticaoBasicaChalenge() {
		given().log().all().auth().preemptive().basic("admin", "senha").when()
				.get("https://restapi.wcaquino.me/basicauth2").then().log().all().statusCode(200)
				.body("status", is("logado"));
	}
	//login brito@brito	senha 123456 conta criada judbrito
	@Test
	public void deveFazerAutenticaoComTokenJWT() {
		Map<String, String> login = new HashMap<String, String>();
		login.put("email","brito@brito");
		login.put("senha","123456");
		//login na api
		//receber token
	String token	= given()
			.log().all()		
			.body(login)
			.contentType(ContentType.JSON)
			.when()
				.post("http://barrigarest.wcaquino.me/signin")
				.then().log().all().statusCode(200)
				.extract().path("token");
		
		given().log().all()
		.header("Authorization", "JWT "+token)
		.when()
			.get("http://barrigarest.wcaquino.me/contas")
			.then().log().all().statusCode(200)
		.body("nome", hasItem ("judbrito"));
	
	}
	@Test
	public void deveAcessarAplicacaoWeb() {
		//login
		String cookie	= given()
				.log().all()
				.formParam("email","brito@brito")
				.formParam("senha","123456")
				.contentType(ContentType.URLENC.withCharset("UTF-8"))
				.when()
				.post("http://seubarriga.wcaquino.me/logar")
				.then()
				.log().all()
				.statusCode(200)
				.extract().header("set-cookie");
		
		cookie = cookie.split("=")[1].split(";")[0];
		System.out.println(cookie);
		//obter conta
		String body = given()
		.log().all()
		.cookie("connect.sid", cookie)
		.when()
			.get("http://seubarriga.wcaquino.me/contas")
			.then()
			.log().all()
			.statusCode(200)
		.body("html.body.table.tbody.tr[0].td[0]", is ("Conta Teste"))
		.extract().body().asString();
		
		System.out.println("______________");
		XmlPath xmlpath = new XmlPath(CompatibilityMode.HTML, body);
		System.out.println(xmlpath.getString("html.body.table.tbody.tr[0].td[0]"));
	
	}
}