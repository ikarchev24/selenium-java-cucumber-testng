package StepDefs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BaseStepDef {

    protected WebDriver driver;

    public void setup(String url) {
        System.setProperty("webdriver.chrome.driver", "D:\\Courses\\QA Complete\\Selenium\\Homework2\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    public void tearDown() {
        driver.quit();
    }
}
