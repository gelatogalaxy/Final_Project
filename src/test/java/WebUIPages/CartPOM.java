package WebUIPages;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;

public class CartPOM extends BasePage {
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
        super(driver);
    }

    public void goToCartPage(){
        openUrl("https://www.demoblaze.com/cart.html");
    }

    public void clickPlaceOrderButton(){
        // tunggu isi cart ter-load dulu supaya total harga sudah benar saat order
        waitUntilVisible(cartItemRow);
        click(placeOrderButton);
    }

    public void fillInOrderForm(DataTable dataTable){
        Map<String, String> formData = dataTable.asMap(String.class, String.class);

        type(nameField, formData.get("Name"));
        type(countryField, formData.get("Country"));
        type(cityField, formData.get("City"));
        type(cardField, formData.get("Credit card"));
        type(monthField, formData.get("Month"));
        type(yearField, formData.get("Year"));
    }

    public void clickPurchaseButton(){
        click(purchaseButton);
    }

    public String getPurchaseTitle(){
        return getText(purchaseTitle);
    }

    public String getResponseText(){
        return getText(purchaseResponse);
    }
}
