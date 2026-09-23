package WebUIPages;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class CartPOM {
    WebDriver driver;
    By placeOrderButton = By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button");
    By nameField = By.id("name");
    By countryField = By.id("country");
    By cityField = By.id("city");
    By cardField = By.id("card");
    By monthField = By.id("month");
    By yearField = By.id("year");
    By purchaseButton = By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]");
    By purchaseResponse = By.xpath("/html/body/div[10]/p");


    public void goToCartPage(){
        driver.get("https://www.demoblaze.com/cart.html");
    }

    public void clickPlaceOrderButton(){
        driver.findElement(placeOrderButton).click();
    }

    public void fillInOrderForm(DataTable dataTable){
        Map<String, String> formData = dataTable.asMap(String.class, String.class);

        String name = formData.get("Name");
        String country = formData.get("Country");
        String city = formData.get("City");
        String card = formData.get("Credit card");
        String month = formData.get("Month");
        String year = formData.get("Year");

        driver.findElement(nameField).sendKeys(name);
        driver.findElement(countryField).sendKeys(country);
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(cardField).sendKeys(card);
        driver.findElement(monthField).sendKeys(month);
        driver.findElement(yearField).sendKeys(year);

    }
    public void clickPurchaseButton(){
        driver.findElement(purchaseButton).click();
    }

    public String getResponseText(){
        return driver.findElement(purchaseResponse).getText();
    }



}
