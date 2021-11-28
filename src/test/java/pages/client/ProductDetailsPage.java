package pages.client;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class ProductDetailsPage extends BasePage {

    @FindBy(css = ".rating > p > a:nth-of-type(2)")
    WebElement writeReviewLink;

    @FindBy(css = ".rating > p > a:nth-of-type(1)")
    WebElement reviewsLink;

    @FindBy(css = "input#input-name.form-control")
    WebElement inputNameReview;

    @FindBy(css = "textarea#input-review.form-control")
    WebElement inputBodyReview;

    @FindBy(css = "button#button-review.btn.btn-primary")
    WebElement submitReviewBtn;

    @FindBy(css = "div.alert.alert-success.alert-dismissible")
    WebElement reviewSubmissionSuccessDiv;


    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getWriteReviewLink() {
        return writeReviewLink;
    }

    public WebElement getInputNameReview() {
        return inputNameReview;
    }

    public WebElement getInputBodyReview() {
        return inputBodyReview;
    }

    public WebElement getSubmitReviewBtn() {
        return submitReviewBtn;
    }

    public WebElement getReviewSubmissionSuccessDiv() {
        return reviewSubmissionSuccessDiv;
    }

    public WebElement getReviewsLink() {
        return reviewsLink;
    }

    public void writeReview(String name, String reviewBody, int rating) throws InterruptedException {
        if(name.isEmpty() || reviewBody.isEmpty()) {
            throw new RuntimeException("Review name and body cannot be empty!");
        }

        if (rating < 1 || rating > 5) {
            throw new RuntimeException("Rating should be between the range 1-5!");
        }

        if (reviewBody.length() < 25) {
            throw new RuntimeException("Review body should be at least 25 chars!");
        }

        scrollToElement(submitReviewBtn);

        fillData(inputNameReview, name);

        fillData(inputBodyReview, reviewBody);

        WebElement rate = driver.findElement(By.xpath("//input[@type='radio'][@value='" + rating + "']"));
        click(rate);

        click(submitReviewBtn);
    }
}
