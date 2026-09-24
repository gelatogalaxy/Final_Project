package WebUIPages;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class CartPOM {
    WebDriver driver;
    WebDriverWait wait;
    By placeOrderButton = By.xpath("//button[text()='Place Order']");
    By cartItemRow = By.cssSelector("#tbodyid tr");
    By nameField = By.id("name");
    By countryField = By.id("country");
    By cityField = By.id("city");
    By cardField = By.id("card");
    By monthField = By.id("month");
    By yearField = By.id("year");
    By purchaseButton = By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]");
    By purchaseTitle = By.cssSelector(".sweet-alert h2");
    By purchaseResponse = By.cssSelector(".sweet-alert p");

    public CartPOM(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToCartPage(){
        driver.get("https://www.demoblaze.com/cart.html");
    }

    public void clickPlaceOrderButton(){
        // tunggu isi cart ter-load dulu supaya total harga sudah benar saat order
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemRow));
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
    }

    public void fillInOrderForm(DataTable dataTable){
        Map<String, String> formData = dataTable.asMap(String.class, String.class);

        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(formData.get("Name"));
        driver.findElement(countryField).sendKeys(formData.get("Country"));
        driver.findElement(cityField).sendKeys(formData.get("City"));
        driver.findElement(cardField).sendKeys(formData.get("Credit card"));
        driver.findElement(monthField).sendKeys(formData.get("Month"));
        driver.findElement(yearField).sendKeys(formData.get("Year"));
    }

    public void clickPurchaseButton(){
        wait.until(ExpectedConditions.elementToBeClickable(purchaseButton)).click();
    }

    public String getPurchaseTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(purchaseTitle)).getText();
    }

    public String getResponseText(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(purchaseResponse)).getText();
    }
}
