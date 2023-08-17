package rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
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
@Test
public void deveObterclima() {
given()
.log().all()
.queryParam("q","Fortaleza,BR")
.queryParam("appid","6bfe1ceed23fa95ff2c38a8353e830de")
.queryParam("units","metric")
.when()
.get("https://api.openweathermap.org/data/2.5/weather")
.then()
.log().all()
.statusCode(200)
.body("name", is("Fortaleza"))
.body("coord.lon", is(-38.5247f))
.body("main.temp", greaterThan(25f));
}
}
//https://api.openweathermap.org/data/2.5/weather?q=Fortaleza,BR&appid=6bfe1ceed23fa95ff2c38a8353e830de&units=metric
