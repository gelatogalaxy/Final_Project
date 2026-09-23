package WebUIStepDefs;
import WebUIPages.CartPOM;
import WebUIPages.HomePagePOM;
import WebUIPages.SamsungGalaxyS6POM;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WebUIStepdefs {

    WebDriver driver;
    HomePagePOM homePage;
    SamsungGalaxyS6POM SamsungGalaxyS6Page;
    CartPOM cartPage;

    @Before
    private WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            if (System.getenv("CI") != null) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
            }
            String chromeBinary = System.getenv("CHROME_BIN") != null
                    ? System.getenv("CHROME_BIN")
                    : System.getenv("CHROME_PATH");
            if (chromeBinary != null && !chromeBinary.isEmpty()) {
                options.setBinary(chromeBinary);
            }
            driver = WebDriverManager.chromedriver().capabilities(options).create();
        }
        return driver;
    }
    @Given("user is on homepage")
    public void userIsOnHomepage(){
        homePage = new HomePagePOM(driver);
        homePage.goToHomePage();
    }

    @When("user clicks log in on the menu")
    public void userClicksLogInOnTheMenu() {
        homePage.clickLogInMenuButton();
    }

    @And("user input username with {string}")
    public void userInputUsernameWith(String usernameInput) {
        homePage.inputUsername(usernameInput);
    }

    @And("user input password with {string}")
    public void userInputPasswordWith(String passwordInput) {
        homePage.inputPassword(passwordInput);
    }

    @When("user clicks log in button")
    public void userClicksLogInButton() {
        homePage.clickFinalLogInButton();
    }

    @Then("user sees {string} on home page")
    public void userSeesOnHomePage(String expectedWelcomeMessage) {
        String receivedWelcomeMessage = homePage.getWelcomeMessage();
        assertEquals(expectedWelcomeMessage, receivedWelcomeMessage);
    }

    @Then("user sees error {string}")
    public void userSeesError(String errorMessage) {
        String receivedErrorMessage = homePage.getAlertMessage();
        assertEquals(errorMessage, receivedErrorMessage);
    }

    @And("user is logged in as Erien")
    public void userIsLoggedInAsErien() {
        String welcomeText = homePage.getWelcomeMessage();
        assertEquals(welcomeText, "Welcome Erien");
    }

    @When("user clicks on product name Samsung galaxy s6")
    public void userClicksOnProductNameSamsungGalaxyS6(){
        homePage.clickOnSamsungGalaxyS6();
    }

    @Then("user sees Add to cart button on Samsung Galaxy S6 product page")
    public void userSeesAddToCartButtonOnSamsungGalaxyS6ProductPage() {
        String welcomeText = SamsungGalaxyS6Page.getWelcomeMessage();
        assertEquals(welcomeText, "Welcome Erien");
    }

    @Given("user is on Samsung galaxy s6 product page")
    public void userIsOnProductPage() {
        SamsungGalaxyS6Page.goToSamsungGalaxyS6POM();
    }

    @When("user clicks on Add to cart button")
    public void userClicksOnAddToCartButton() {
        SamsungGalaxyS6Page.clickAddToCartButton();
    }

    @Then("user sees message {string}")
    public void userSeesMessage(String expectedSuccessMessage) {
        String receivedMessage = SamsungGalaxyS6Page.getSuccessfulAlertMessage();
        assertEquals(receivedMessage, expectedSuccessMessage);
    }

    @Given("user is on cart page")
    public void userIsOnCartPage() {
        cartPage.goToCartPage();
    }

    @When("user clicks on Place order button")
    public void userClicksOnPlaceOrderButton() {
        cartPage.clickPlaceOrderButton();
    }

    @And("user input details:")
    public void userInputDetails(DataTable dataTable) {
        cartPage.fillInOrderForm(dataTable);
    }

    @And("user clicks Purchase button")
    public void userClicksPurchaseButton() {
        cartPage.clickPurchaseButton();
    }


    @And("user sees {string} and {string}")
    public void userSeesAmountFieldIsAndCardNumberFieldIs(String amountField, String cardField) {
        String responseText = cartPage.getResponseText();
        assertTrue(responseText.contains(amountField));
        assertTrue(responseText.contains(cardField));
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

