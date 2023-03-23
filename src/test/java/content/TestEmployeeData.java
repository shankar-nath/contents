package content;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class TestEmployeeData {

    private WebDriver driver;
    private String url = "https://jsonplaceholder.typicode.com/comments";

    @Before
    public void setUp() {
        try {
            System.out.println("Starting...");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
            options.setAcceptInsecureCerts(true);
            options.addArguments("--remote-allow-origins=*");
            options.setHeadless(true);
            driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(1));
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    @Test
    public void validateEmployeeData(){
        String uiResponse = getEmployeeUIData(url);
        String apiResponse = getEmployeeAPIData(url);
        Assert.assertTrue("Employee Data between API response and UI is wrong", uiResponse.equals(apiResponse));
    }

    private String getEmployeeAPIData(String uri) {

        Response responseObj = RestAssured.given().contentType(ContentType.JSON).when().get(uri).then().extract().response();
        Assert.assertEquals( "getEmployeeAPIData Failed", 200, responseObj.getStatusCode());

        String jsonResponse = responseObj.getBody().asString();
        return jsonResponse;
    }

    private String getEmployeeUIData(String uri) {
        driver.get(uri);
        String uiResponse = driver.getPageSource();

        uiResponse = uiResponse.replace("<html><head><meta name=\"color-scheme\" content=\"light dark\"></head><body><pre style=\"word-wrap: break-word; white-space: pre-wrap;\">", "");
        uiResponse = uiResponse.replace("</pre></body></html>", "");

        driver.close();
        driver.quit();
        return uiResponse;
    }



}
