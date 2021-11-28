package pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class NavigationDashboard extends BasePage {

    @FindBy(css = "#menu-sale > a")
    WebElement menuSales;

    @FindBy(css = "#menu-catalog > a")
    WebElement menuCatalog;

    @FindBy(css = "#collapse1 > li:nth-of-type(9)")
    WebElement menuCatalogReviewsSubMenu;

    @FindBy(css = "#collapse4 > li:nth-of-type(4)")
    WebElement giftVouchersSubMenu;

    @FindBy(css = "#collapse4 > li:nth-of-type(4) > ul > li:nth-of-type(1)")
    WebElement giftVouchers;

    public NavigationDashboard(WebDriver driver) {
       super(driver);
    }

    public WebElement getMenuSales() {
        return menuSales;
    }

    public WebElement getGiftVouchersSubMenu() {
        return giftVouchersSubMenu;
    }

    public WebElement getGiftVouchers() {
        return giftVouchers;
    }

    public WebElement getMenuCatalog() {
        return menuCatalog;
    }

    public WebElement getMenuCatalogReviewsSubMenu() {
        return menuCatalogReviewsSubMenu;
    }

    public GiftVouchersPage navigateToGiftVouchersMenu() {
        click(menuSales);
        click(giftVouchersSubMenu);
        click(giftVouchers);
        return new GiftVouchersPage(driver);
    }

    public ProductReviewsPage navigateToProductReviews() {
        click(menuCatalog);
        click(menuCatalogReviewsSubMenu);
        return new ProductReviewsPage(driver);
    }
}
