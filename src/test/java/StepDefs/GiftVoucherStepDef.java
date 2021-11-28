package StepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.admin.GiftVouchersPage;
import pages.admin.LoginPage;
import pages.admin.NavigationDashboard;

public class GiftVoucherStepDef extends BaseStepDef {

    private GiftVouchersPage giftVouchersPage;

    @Before
    public void testSetup() {
       setup("http://shop.pragmatic.bg/admin");
    }

    @After
    public void testTearDown() {
       tearDown();
    }

    @Given("the user is logged as admin")
    public void theUserIsLoggedAsAdmin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin11", "parola123!");
    }

    @And("the user navigates to gift vouchers menu and deletes any existing vouchers")
    public void theUserNavigatesToGiftVouchersMenuAndDeletesAnyExistingVouchers() {
        giftVouchersPage = new NavigationDashboard(driver).navigateToGiftVouchersMenu();
        giftVouchersPage.deleteVouchers();
    }

    @When("the user creates new voucher")
    public void theUserCreatesNewVoucher() {
        giftVouchersPage.click(giftVouchersPage.getAddVoucherBtn());
        giftVouchersPage.addGiftVoucher("code1", "Test Test", "test@example.com", "Test 2", "test2@example.com", 51.99);
    }

    @Then("the voucher is successfully created")
    public void theVoucherIsSuccessfullyCreated() {
        Assert.assertTrue(giftVouchersPage.childElementContainsText(giftVouchersPage.getVouchersView(), "code1"));
        Assert.assertTrue(giftVouchersPage.isWebElementDisplayed(giftVouchersPage.getVoucherEditBtn()), "Voucher was not created!");
    }
}
