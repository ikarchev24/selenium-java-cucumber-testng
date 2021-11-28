package pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GiftVouchersPage extends NavigationDashboard {

    @FindBy(css = ".btn.btn-primary")
    WebElement addVoucherBtn;

    @FindBy(css = ".btn.btn-danger")
    WebElement deleteVoucherBtn;

    @FindBy(xpath = "//input[@name='selected[]']")
    WebElement voucherCheckbox;

    @FindBy(xpath = "//input[@onclick=\"$('input[name*=\\'selected\\']').prop('checked', this.checked);\"]")
    WebElement selectAllVouchersCheckbox;

    @FindBy(css = ".alert.alert-success.alert-dismissible")
    WebElement voucherDeletedCreatedSuccessDiv;

    @FindBy(css = ".fa.fa-pencil")
    WebElement voucherEditBtn;

    @FindBy(css = ".panel-body")
    WebElement vouchersView;

    public GiftVouchersPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getAddVoucherBtn() {
        return addVoucherBtn;
    }

    public WebElement getVoucherCheckbox() {
        return voucherCheckbox;
    }

    public WebElement getDeleteVoucherBtn() {
        return deleteVoucherBtn;
    }

    public WebElement getVoucherDeletedCreatedSuccessDiv() {
        return voucherDeletedCreatedSuccessDiv;
    }

    public WebElement getVoucherEditBtn() {
        return voucherEditBtn;
    }

    public WebElement getVouchersView() {
        return vouchersView;
    }

    public void deleteVouchers() {
        if (isWebElementDisplayed(voucherCheckbox)) {
            String currentWindow = driver.getWindowHandle();
            click(selectAllVouchersCheckbox);
            click(deleteVoucherBtn);
            driver.switchTo().alert().accept();
            driver.switchTo().window(currentWindow);
            waitForVisibilityOfElement(voucherDeletedCreatedSuccessDiv, 5);
        }
    }

    public WebElement getSelectAllVouchersCheckbox() {
        return selectAllVouchersCheckbox;
    }

    public void addGiftVoucher(String code, String fromName, String fromEmail, String toName, String toEmail, double amount) {
        waitForVisibilityOfElement(By.cssSelector("#input-code"), 5);
        driver.findElement(By.cssSelector("#input-code")).sendKeys(code);
        driver.findElement(By.cssSelector("#input-from-name")).sendKeys(fromName);
        driver.findElement(By.cssSelector("#input-from-email")).sendKeys(fromEmail);
        driver.findElement(By.cssSelector("#input-to-name")).sendKeys(toName);
        driver.findElement(By.cssSelector("#input-to-email")).sendKeys(toEmail);
        driver.findElement(By.cssSelector("#input-amount")).sendKeys(String.valueOf(amount));
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();
        waitForVisibilityOfElement(voucherDeletedCreatedSuccessDiv, 5);
    }
}
