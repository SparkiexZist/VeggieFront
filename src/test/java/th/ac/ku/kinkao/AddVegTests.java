package th.ac.ku.kinkao;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddVegTests
{
    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 1000);
    }

    @BeforeEach
    public void beforeEach() {
        driver.get("http://localhost:" + port + "/login");
    }

    @AfterEach
    public void afterEach() throws InterruptedException {
        Thread.sleep(3000);
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @Test
    void testAddVeg()
    {
        WebElement usernameField = wait.until(webDriver -> webDriver.findElement(By.id("username")));
        WebElement passwordField = driver.findElement(By.id("password"));

        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameField.sendKeys("sparkie");
        passwordField.sendKeys("1234");
        submitButton.click();

        driver.get("http://localhost:" + port + "/vegetable");

        WebElement increaseButton = wait.until(webDriver -> webDriver.findElement(By.id("increase")));
        increaseButton.click();
        WebElement addButton = driver.findElement(By.id("add"));
        addButton.click();

        driver.get("http://localhost:"+ port + "/cart");

        WebElement name = wait.until(webDriver -> webDriver.findElement(By.xpath("//table/tbody/tr[1]/td[2]")));
        WebElement author = driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]"));
        WebElement price = driver.findElement(By.xpath("//table/tbody/tr[1]/td[4]"));

        assertEquals("Cabbage", name.getText());
        assertEquals("20.0", author.getText());
        assertEquals("1", price.getText());

    }

}
