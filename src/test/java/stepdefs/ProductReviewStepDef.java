package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.admin.LoginPage;
import pages.admin.NavigationDashboard;
import pages.admin.ProductReviewsPage;
import pages.client.HomePage;
import pages.client.ProductDetailsPage;
import pages.client.ProductsPage;

public class ProductReviewStepDef extends BaseStepDef {

    private final String name = "Ivo " + System.currentTimeMillis();
    private final int rating = 5;
    private final String iMac = "iMac";
    private int currentNumberOfReviews;

    @Before
    public void testSetup() {
        setup("http://shop.pragmatic.bg");
    }

    @After
    public void testTearDown() {
        tearDown();
    }

    @Given("the user opens specific product page")
    public void theUserOpensSpecificProductPage() {
        HomePage homePage = new HomePage(driver);
        homePage.moveToElement(homePage.getNavBarDesktops());
        homePage.click(homePage.getNavBarDesktopsMac());
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.selectProductByText(iMac);
    }

    @When("the user writes a review for that product")
    public void theUserWritesAReviewForThatProduct() throws InterruptedException {
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        productDetailsPage.click(productDetailsPage.getWriteReviewLink());
        currentNumberOfReviews = Integer.parseInt(productDetailsPage.getReviewsLink().getText().split(" ")[0]);
        productDetailsPage.writeReview(name, "The product was delivered fast and well-packaged!", rating);
        productDetailsPage.waitForVisibilityOfElement(productDetailsPage.getReviewSubmissionSuccessDiv(), 5);
        Assert.assertEquals(productDetailsPage.getReviewSubmissionSuccessDiv().getText(), "Thank you for your review. It has been submitted to the webmaster for approval.");
    }

    @And("when an admin logins to the backend")
    public void whenAnAdminLoginsToTheBackend() {
        driver.navigate().to("http://shop.pragmatic.bg/admin/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin11", "parola123!");
    }

    @And("the admin navigates to reviews page")
    public void theAdminNavigatesToReviewsPage() {
        NavigationDashboard navigationDashboard = new NavigationDashboard(driver);
        navigationDashboard.navigateToProductReviews();
    }

    @Then("the admin sees the review for the product is present")
    public void theAdminSeesTheReviewForTheProductIsPresent() {
        ProductReviewsPage productReviewsPage = new ProductReviewsPage(driver);
        Assert.assertTrue(productReviewsPage.childElementContainsText(productReviewsPage.getReviewsView(), name));
        Assert.assertTrue(productReviewsPage.childElementContainsText(productReviewsPage.getReviewsView(), String.valueOf(rating)));
    }

    @And("when the admin approves the review")
    public void whenTheAdminApprovesTheReview() {
        ProductReviewsPage productReviewsPage = new ProductReviewsPage(driver);
        productReviewsPage.click(productReviewsPage.getEditReview());
        productReviewsPage.approveReview();
    }

    @And("the user navigates back to the respective product page")
    public void theUserNavigatesBackToTheRespectiveProductPage() {
        driver.navigate().to("http://shop.pragmatic.bg");
        theUserOpensSpecificProductPage();
    }

    @Then("the user sees number of reviews incremented by one")
    public void theUserSeesNumberOfReviewsIncrementedByOne() {
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        Assert.assertEquals(Integer.parseInt(productDetailsPage.getReviewsLink().getText().split(" ")[0]), currentNumberOfReviews + 1);
    }
}

