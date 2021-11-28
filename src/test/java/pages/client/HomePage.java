package pages.client;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[text()='Desktops']")
    WebElement navBarDesktops;

    @FindBy(css = ".list-unstyled > li:nth-of-type(2)")
    WebElement navBarDesktopsMac;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getNavBarDesktops() {
        return navBarDesktops;
    }

    public WebElement getNavBarDesktopsMac() {
        return navBarDesktopsMac;
    }
}
