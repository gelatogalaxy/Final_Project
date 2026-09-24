package WebUIPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SamsungGalaxyS6POM {
    WebDriver driver;
    WebDriverWait wait;
    By productTitle = By.xpath("//*[@id=\"tbodyid\"]/h2");
    By addToCartButton = By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a");

    public SamsungGalaxyS6POM(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToSamsungGalaxyS6POM(){
        driver.get("https://www.demoblaze.com/prod.html?idp_=1");
    }

    public String getProductTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle)).getText();
    }

    public boolean isAddToCartButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton)).isDisplayed();
    }

    public void clickAddToCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    public String getSuccessfulAlertMessage(){
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }
}
