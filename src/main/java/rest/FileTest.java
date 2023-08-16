package rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

import java.io.File;

import org.junit.Test;

public class FileTest {

	//@Test
	public void deveObrigarEnvioArquivo() {
		given().log().all().when().post("http://restapi.wcaquino.me/upload").then().log().all().statusCode(404)
				.body("error", is("Arquivo não enviado"));
	}

	@Test
	public void deveFazerUploadArquivo() {
		given().log().all()
		.multiPart("arquivo", new File("src/main/resources/user2.pdf"))
		.when()
				.post("http://restapi.wcaquino.me/upload")
				.then().log().all().statusCode(200)
				.body("name", is("user2.pdf"));
	}
	//arquivo tem 47kb por isso deixei lessThan de 1200L
	@Test
	public void nãodeveFazerUploadArquivoGrande() {
		given().log().all()
		.multiPart("arquivo", new File("src/main/resources/user2.pdf"))
		.when()
				.post("http://restapi.wcaquino.me/upload")
				.then().log().all()
				.time(lessThan(1200L))
		.statusCode(200);
	}

}
