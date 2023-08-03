package rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class UserXmlTest {
	@Test
	public void devoTrabalharComXml() {
		given().when().get("https://restapi.wcaquino.me/usersXMl/3").then().statusCode(200)
				.body("user.name", is("Ana Julia")).body("user.@id", is("3")).body("user.filhos.name.size()", is(2))
				.body("user.filhos.name[0]", is("Zezinho")).body("user.filhos.name[1]", is("Luizinho"))
				.body("user.filhos.name", hasItem("Luisinho")).body("user.filhos.name", hasItems("Zezinho", "Luisinho"))
				.body("user.filhos.name.size()", is(2));

	}

	@Test
	public void devoTrabalharComXmlExemplo2() {
		given().when().get("https://restapi.wcaquino.me/usersXMl/3").then().statusCode(200)

				.rootPath("user").body(".name", is("Ana Julia")).body("@id", is("3"))

				.rootPath("user.filhos").body("name.size()", is(2))

				.detachRootPath("filhos").body("filhos.name[0]", is("Zezinho")).body("filhos.name[1]", is("Luizinho"))

				.appendRootPath("filhos").body("name", hasItem("Luisinho")).body("name.size()", is(2))
				.body("name", hasItems("Zezinho", "Luisinho"));

	}

	@Test
	public void devoFazerPesquisasAvancadas() {
		given().when().get("https://restapi.wcaquino.me/usersXMl").then().statusCode(200)
				.body("users.user.size()", is(3)).body("users.user.findAll{it.age.toInterger()<=25}.size()", is(2))
				.body("users.user.@id", hasItems("1", "2", "3"))
				.body("users.user.findAll{it.name.toString().contains('n')}.name",
						hasItems("Maria Joaquina", "Ana Julia"))
				.body("users.user.salary,find{it.!null}.toDouble()", is(1234.5678d))
				.body("users.user.age.collect{it.toInterger()*2}", hasItems(40, 50, 60));

	}
}
