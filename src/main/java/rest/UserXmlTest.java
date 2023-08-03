package rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

public class UserXmlTest {
	public void devoTrabalharComXml() {
		given().when().get("https://restapi.wcaquino.me/usersXMl/3").then().statusCode(200)
				.body("user.name", is("Ana Julia")).body("user.@id", is("3")).body("user.filhos.name.size()", is(2))
				.body("user.filhos.name[0]", is("Zezinho")).body("user.filhos.name[1]", is("Luizinho"))
				.body("user.filhos.name", hasItem("Luisinho")).body("user.filhos.name", hasItems("Zezinho", "Luisinho"))
				.body("user.filhos.name.size()", is(2));

	}
	public class UserXmlTestExemplo2 {
		public void devoTrabalharComXml() {
			given().when().get("https://restapi.wcaquino.me/usersXMl/3")
			.then().statusCode(200)
			.rootPath("user")
					.body(".name", is("Ana Julia"))
					.body("@id", is("3"))
					.rootPath("user.filhos")
					.body("name.size()", is(2))
					.datachRootPath("filhos")
					.body("name[0]", is("Zezinho"))
					.body("name[1]", is("Luizinho"))
					.apeendRootPAth("datachRootPath")
					.body("name", hasItem("Luisinho"))
					.body("name.size()", is(2));
					.body("name", hasItems("Zezinho", "Luisinho"))

		}
}
