package pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductReviewsPage extends NavigationDashboard {

    private static final String VALUE_ENABLED = "1";
    private static final String VALUE_DISABLED = "2";

    @FindBy(css = "div.col-md-9.col-md-pull-3.col-sm-12 > .panel")
    WebElement reviewsView;

    @FindBy(css = ".fa.fa-pencil")
    WebElement editReview;

    @FindBy(css = "select#input-status.form-control")
    WebElement selectStatusDropDown;

    @FindBy(css = "i.fa.fa-save")
    WebElement saveReviewBtn;

    public ProductReviewsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getReviewsView() {
        return reviewsView;
    }

    public WebElement getEditReview() {
        return editReview;
    }

    public WebElement getSelectStatusDropDown() {
        return selectStatusDropDown;
    }

    public WebElement getSaveReviewBtn() {
        return saveReviewBtn;
    }

    public void approveReview() {
        click(editReview);
        waitForVisibilityOfElement(selectStatusDropDown, 5);
        Select select = new Select(selectStatusDropDown);
        select.selectByValue(VALUE_ENABLED);
        click(saveReviewBtn);
    }
}
