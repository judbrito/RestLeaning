package rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserXmlTest {
public void devoTrabalharComXml() {
	given()
	.when()
		.get("https://restapi.wcaquino.me/usersXMl/3")
.then()
	.statusCode(200)
	.body("user.name",is("Ana Julia"))
	.body("user.@id", is("3"))
	.body("user.filhos.name.size()", is (2))
	.body("user.filhos.name[0]", is ("Zezinho"))
	.body("user.filhos.name[1]", is ("Luizinho"))
	.body("user.filhos.name",hasItem("Luisinho"))
	.body("user.filhos.name",hasItems("Zezinho", "Luisinho"))
	.body("user.filhos.name.size()", is (2));

}
}
