package WebUIPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Superclass untuk semua Page Object (POM). Sebelumnya tiap POM (HomePagePOM,
// SamsungGalaxyS6POM, CartPOM) membuat WebDriverWait sendiri-sendiri dan mengulang
// pola wait.until(ExpectedConditions...) di banyak tempat. BasePage menyatukan
// driver/wait dan helper aksi umum supaya POM turunan tinggal panggil helper-nya.
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void openUrl(String url) {
        driver.get(url);
    }

    protected WebElement waitUntilVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitUntilClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        waitUntilClickable(locator).click();
    }

    protected void type(By locator, String text) {
        waitUntilVisible(locator).sendKeys(text);
    }

    protected String getText(By locator) {
        return waitUntilVisible(locator).getText();
    }
}
