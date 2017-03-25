package tests;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.greaterThan;

import com.jayway.restassured.http.ContentType;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import testcases.BaseApiTestCase;

public class SalesFlowApiTest extends BaseApiTestCase{

  @Before
  public void setUp(){
    super.setUp();
  }

  @Test
  public void completeSalesFlowTest(){
    Map<String,String> manufacturers = given()
        .param(AUTH_TOKEN_KEY, AUTH_TOKEN_VALUE)
        .param(LOCALE_KEY, LOCALE_VALUE)
    .when()
        .get(MANUFACTURER_ENDPOINT)
    .then()
        .statusCode(HTTP_OK)
        .contentType(ContentType.JSON)
        .body(RETRIEVED_ITEMS_AMOUNT_PATH, greaterThan(0))
        .body(matchesJsonSchemaInClasspath(WRAPPING_JSON_SCHEMA))
    .extract()
        .path(RETRIEVED_ITEMS_PATH);

    String sampleManufacturer = manufacturers.keySet().iterator().next();

    Map<String,String> models = given()
        .param(AUTH_TOKEN_KEY, AUTH_TOKEN_VALUE)
        .param(LOCALE_KEY, LOCALE_VALUE)
        .param(MANUFACTURER_KEY, sampleManufacturer)
    .when()
        .get(MAIN_TYPES_ENDPOINT)
    .then()
        .statusCode(HTTP_OK)
        .contentType(ContentType.JSON)
        .body(RETRIEVED_ITEMS_AMOUNT_PATH, greaterThan(0))
        .body(matchesJsonSchemaInClasspath(WRAPPING_JSON_SCHEMA))
    .extract()
        .path(RETRIEVED_ITEMS_PATH);

    String sampleModel = models.keySet().iterator().next();

    given()
        .param(AUTH_TOKEN_KEY, AUTH_TOKEN_VALUE)
        .param(LOCALE_KEY, LOCALE_VALUE)
        .param(MANUFACTURER_KEY, sampleManufacturer)
        .param(MAIN_TYPE_KEY, sampleModel)
    .when()
        .get(BUILT_DATES_ENDPOINT)
    .then()
        .statusCode(HTTP_OK)
        .contentType(ContentType.JSON)
        .body(RETRIEVED_ITEMS_AMOUNT_PATH, greaterThan(0))
//        .body(matchesJsonSchemaInClasspath(WRAPPING_JSON_SCHEMA))
    ;
  }
}
