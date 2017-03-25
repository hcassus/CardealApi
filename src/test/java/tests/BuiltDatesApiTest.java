package tests;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import com.jayway.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import testcases.BaseApiTestCase;

public class BuiltDatesApiTest extends BaseApiTestCase{

  @Before
  public void setUp(){
    super.setUp();
  }

  @Test
  public void getBuiltDatesSuccessfullyTest(){
    given()
        .param(AUTH_TOKEN_KEY, AUTH_TOKEN_VALUE)
        .param(LOCALE_KEY, LOCALE_VALUE)
        .param(MANUFACTURER_KEY, MANUFACTURER_DEFAULT_VALUE)
        .param(MAIN_TYPE_KEY, MAIN_TYPE_DEFAULT_VALUE)
    .when()
        .get(BUILT_DATES_ENDPOINT)
    .then()
        .statusCode(HTTP_OK)
        .contentType(ContentType.JSON)
        .body(matchesJsonSchemaInClasspath(WRAPPING_JSON_SCHEMA))
    ;
  }

  @Test
  public void noWaKeyShouldReceiveUnauthorizedResponseTest(){
    given()
        .param(AUTH_TOKEN_KEY, "")
        .param(LOCALE_KEY, LOCALE_VALUE)
        .param(MANUFACTURER_KEY, MANUFACTURER_DEFAULT_VALUE)
        .param(MAIN_TYPE_KEY, MAIN_TYPE_DEFAULT_VALUE)
    .when()
        .get(BUILT_DATES_ENDPOINT)
    .then()
        .statusCode(HTTP_UNAUTHORIZED)
 ;
  }

}
