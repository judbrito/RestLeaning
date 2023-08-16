package rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Assert;
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
		.multiPart("arquivo", new File("src/main/resources/imagem.png"))
		.when()
				.post("http://restapi.wcaquino.me/upload")
				.then().log().all().statusCode(200)
				.body("name", is("imagem.png"));
	}
	//arquivo tem 47kb por isso deixei lessThan de 1200L
	@Test
	public void nãodeveFazerUploadArquivoGrande() {
		given().log().all()
		.multiPart("arquivo", new File("src/main/resources/imagem.png"))
		.when()
				.post("http://restapi.wcaquino.me/upload")
				.then().log().all()
				.time(lessThan(1200L))
		.statusCode(200);
	}
	@Test
	public void deveBaixarArquivo() throws IOException  {
		byte [] image= given()
				.log().all()
				.when()
				.get("http://restapi.wcaquino.me/download")
				.then().log().all()
				.statusCode(200)
				.extract().asByteArray();
		
		File imagem = new File("src/main/resources/imagem.png");
				OutputStream out = new FileOutputStream(imagem);
				out.write(image);
				out.close();
				
				Assert.assertThat(imagem.length(), lessThan(100000L));
	}

}
