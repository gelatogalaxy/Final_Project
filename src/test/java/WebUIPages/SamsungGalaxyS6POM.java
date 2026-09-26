package WebUIPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SamsungGalaxyS6POM extends BasePage {
    By productTitle = By.xpath("//*[@id=\"tbodyid\"]/h2");
    By addToCartButton = By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a");

    public SamsungGalaxyS6POM(WebDriver driver) {
        super(driver);
    }

    public void goToSamsungGalaxyS6POM(){
        openUrl("https://www.demoblaze.com/prod.html?idp_=1");
    }

    public String getProductTitle(){
        return getText(productTitle);
    }

    public boolean isAddToCartButtonDisplayed() {
        return waitUntilVisible(addToCartButton).isDisplayed();
    }

    public void clickAddToCartButton() {
        click(addToCartButton);
    }

    public String getSuccessfulAlertMessage(){
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }
}
