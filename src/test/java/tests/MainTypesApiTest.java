package tests;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import com.jayway.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import testcases.BaseApiTestCase;

public class MainTypesApiTest extends BaseApiTestCase {

  @Before
  public void setUp(){
    super.setUp();
  }

  @Test
  public void getMainTypesTest(){
    given()
        .param(AUTH_TOKEN_KEY, AUTH_TOKEN_VALUE)
        .param(LOCALE_KEY, LOCALE_VALUE)
        .param(MANUFACTURER_KEY, MANUFACTURER_DEFAULT_VALUE)
    .when()
        .get(MAIN_TYPES_ENDPOINT)
    .then()
        .statusCode(HTTP_OK)
        .contentType(ContentType.JSON)
        .body(matchesJsonSchemaInClasspath(WRAPPING_JSON_SCHEMA));
  }

  @Test
  public void noWaKeyShouldReceiveUnauthorizedResponseTest(){
    given()
        .param(AUTH_TOKEN_KEY, "")
        .param(LOCALE_KEY, LOCALE_VALUE)
        .param(MANUFACTURER_KEY, MANUFACTURER_DEFAULT_VALUE)
    .when()
        .get(MAIN_TYPES_ENDPOINT)
    .then()
        .statusCode(HTTP_UNAUTHORIZED)
    ;
  }
}
