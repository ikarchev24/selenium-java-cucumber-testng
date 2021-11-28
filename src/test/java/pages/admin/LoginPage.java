package pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class LoginPage extends BasePage {

    @FindBy(id = "input-username")
    WebElement username;

    @FindBy(id = "input-password")
    WebElement password;

    @FindBy(css = ".btn-primary")
    WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        waitForVisibilityOfElement(this.username, 5);
        fillData(this.username, username);
        fillData(this.password, password);
        click(loginBtn);
    }
}
