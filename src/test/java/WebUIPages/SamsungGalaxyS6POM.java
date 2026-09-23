package WebUIPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SamsungGalaxyS6POM {
    WebDriver driver;
    By welcomeMessage = By.id("nameofuser");
    By productTitle = By.xpath("//*[@id=\"tbodyid\"]/h2");
    By addToCartButton = By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a");

    public void goToSamsungGalaxyS6POM(){
        driver.get("https://www.demoblaze.com/prod.html?idp_=1");
    }

    public String getWelcomeMessage() {
        return driver.findElement(welcomeMessage).getText();
    }
    public String getProductTitle(){
        return driver.findElement(productTitle).getText();
    }

    public void clickAddToCartButton() {
        driver.findElement(addToCartButton).click();
    }
    public String getSuccessfulAlertMessage(){
        return driver.switchTo().alert().getText();
    }

}
