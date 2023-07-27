package rest;

import java.lang.reflect.Method;

import io.restassured.RestAssured;

public class OlaMundo {

	public static void main(String[] args) {

		RestAssured.request(Method.GET, "http://restapi.wcaquino.me");

	}
}