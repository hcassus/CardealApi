package testcases;

import com.jayway.restassured.RestAssured;
import org.junit.Before;

public abstract class BaseApiTestCase {

  private static final String BASE_URL = System.getenv("CAR_BASE_URL");
  private static final String BASE_SERVICE = "/v1/car-types";

  protected static final String MAIN_TYPES_ENDPOINT = BASE_SERVICE + "/main-types";
  protected static final String BUILT_DATES_ENDPOINT = BASE_SERVICE + "/built-dates";
  protected static final String MANUFACTURER_ENDPOINT = BASE_SERVICE + "/manufacturer";

  protected static final String AUTH_TOKEN_KEY = "wa_key";
  protected static final String AUTH_TOKEN_VALUE = System.getenv("CAR_AUTH_TOKEN");
  protected static final String LOCALE_KEY = "locale";
  protected static final String LOCALE_VALUE = "de";
  protected static final String MANUFACTURER_KEY = "manufacturer";
  protected static final String MANUFACTURER_DEFAULT_VALUE = "465";
  protected static final String MAIN_TYPE_KEY = "main-type";
  protected static final String MAIN_TYPE_DEFAULT_VALUE = "Gallardo";

  protected static final int HTTP_OK = 200;
  protected static final int HTTP_UNAUTHORIZED = 401;

  protected static final String WRAPPING_JSON_SCHEMA = "wrapping-schema.json";

  protected static final String RETRIEVED_ITEMS_PATH = "wkda";
  protected static final String RETRIEVED_ITEMS_AMOUNT_PATH = "wkda.keySet().size()";


  @Before
  public void setUp() {
    RestAssured.baseURI = BASE_URL;
  }

}
