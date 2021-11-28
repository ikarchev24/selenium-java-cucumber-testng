package pages.client;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(css = "h4 > a")
    List<WebElement> products;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public ProductDetailsPage selectProductByText(String text) {
        for (WebElement product : products) {
            if (product.getText().equals(text)) {
                click(product);
                return new ProductDetailsPage(driver);
            }
        }
        return null;
    }
}
