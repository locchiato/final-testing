package com.Tests;

import com.Base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class APIRestTest extends BaseTest {
    private static RequestSpecification request;
    private static final String user = "finaluserAPI";

    @BeforeAll
    public static void beforeAll(){
        RestAssured.baseURI ="https://parabank.parasoft.com/parabank";
    }

    @BeforeEach
    public void newRequest(){
        request = given();
    }

    private static String sessionId;
    private static String customerId;
    private static String accountId1;
    private static String accountId2;

    private String successMsgAccount = "ParaBank | Customer Created";

    private String successMsgAccountSummary = "ParaBank | Accounts Overview";

    private String ammountToTransfer = "300";

    @Test
    @Order(0)
    @Tag("Smoke")
    @DisplayName("Proceso de registro")
    public void testRegistry(){

        sessionId = request
                .when()
                .get(baseURI + "/register.htm").sessionId();

        System.out.println("Sessión creada para el registro " + sessionId);
        given()
                .cookie("JSESSIONID", sessionId)
                .contentType("application/x-www-form-urlencoded; charset")
                .formParam("customer.firstName", "Ariel")
                .formParam("customer.lastName","Lucki")
                .formParam("customer.address.street", "Calle falsa 123")
                .formParam("customer.address.city", "Buenos Aires")
                .formParam("customer.address.state", "Argentina")
                .formParam("customer.address.zipCode", "3400")
                .formParam("customer.phoneNumber", "12345612")
                .formParam("customer.ssn", "123")
                .formParam("customer.username", user)
                .formParam("customer.password", PASSWORD)
                .formParam("repeatedPassword", PASSWORD)
                .when()
                .post(baseURI + "/register.htm")
                .then()
                .body("html.head.title", equalTo(successMsgAccount));
    }

    @Test
    @Order(2)
    @Tag("Smoke")
    @DisplayName("Proceso de login")
    public void testLogin(){

        sessionId  = request
                .formParam("username",user)
                .formParam("password",PASSWORD)
                .accept(ContentType.JSON)
                .when()
                .post(baseURI + "/login.htm")
                .then()
                .statusCode(302).log().all().extract().sessionId();

        customerId = request
                .pathParam("username",user)
                .pathParam("password",PASSWORD)
                .accept(ContentType.JSON)
                .when()
                .get(baseURI + "/services/bank/login/{username}/{password}")
                .then()
                .statusCode(200).log().all()
                .extract().path("id").toString();

        System.out.println("customerId: "+customerId);
        System.out.println("sessionId: "+sessionId);
    }

    @Test
    @Order(3)
    @Tag("Smoke")
    @DisplayName("Apertura de una nueva cuenta")
    public void testNewAccount(){

        Response response1 = given()
                .cookie("JSESSIONID", sessionId)
                .pathParam("customerId", customerId)
                .accept(ContentType.JSON)
                .when()
                .get(baseURI + "/services_proxy/bank/customers/{customerId}/accounts")
                .then()
                .statusCode(200).log().all().extract().response();

        accountId1 = response1.jsonPath().getString("id[0]");
        Response response2 = request
                .cookie("JSESSIONID", sessionId)
                .formParam("customerId",customerId)
                .formParam("newAccountType","1")
                .formParam("fromAccountId",accountId1)
                .accept(ContentType.JSON)
                .when()
                .post(baseURI + "/services_proxy/bank/createAccount")
                .then()
                .statusCode(200).log().all().extract().response();

        accountId2 = response2.jsonPath().getString("id");


        System.out.println("Account 1: "+accountId1);
        System.out.println("Account 2: "+accountId2);

    }

    @Test
    @Order(4)
    @Tag("Regression")
    @DisplayName("Resumen de las cuentas")
    public void testAccountSummary() {

        request
                .cookie("JSESSIONID", sessionId)
                .when()
                .get(baseURI + "/overview.htm")

                .then()
                .body("html.head.title", equalTo(successMsgAccountSummary));
    }

    @Test
    @Order(5)
    @Tag("Smoke")
    @DisplayName("Transferencia de fondos")
    public void testTransferFounds(){

        request
                .cookie("JSESSIONID", sessionId)
                .formParam("fromAccountId",accountId1)
                .formParam("toAccountId",accountId2)
                .formParam("amount",ammountToTransfer)
                .when()
                .post(baseURI + "/services_proxy/bank/transfer")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    @Order(6)
    @Tag("Regression")
    @DisplayName("Actividad de la cuenta")
    public void testAccountActivity(){

        request
                .cookie("JSESSIONID", sessionId)
                .pathParam("account",accountId1)
                .accept(ContentType.JSON)
                .when()
                .get(baseURI + "/services_proxy/bank/accounts/{account}/transactions/month/All/type/All")
                .then()
                .statusCode(200).log().all();
    }


}
