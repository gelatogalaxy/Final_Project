package WebUIPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePagePOM {
    WebDriver driver;
    WebDriverWait wait;
    By logInMenuButton = By.id("login2");
    By finalLogInButton = By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]");
    By usernameInputText = By.id("loginusername");
    By passwordInputText = By.id("loginpassword");
    By welcomeMessage = By.id("nameofuser");
    By samsungGalaxyS6Title = By.linkText("Samsung galaxy s6");

    public HomePagePOM(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToHomePage(){
        driver.get("https://www.demoblaze.com/");
    }

    public void clickLogInMenuButton(){
        wait.until(ExpectedConditions.elementToBeClickable(logInMenuButton)).click();
    }

    public void inputUsername(String username){
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInputText)).sendKeys(username);
    }

    public void inputPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInputText)).sendKeys(password);
    }

    public void clickFinalLogInButton(){
        wait.until(ExpectedConditions.elementToBeClickable(finalLogInButton)).click();
    }

    public String getWelcomeMessage(){
        // Teks "Welcome ..." baru muncul setelah request login selesai
        wait.until(ExpectedConditions.textToBePresentInElementLocated(welcomeMessage, "Welcome"));
        return driver.findElement(welcomeMessage).getText();
    }

    public String getAlertMessage(){
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }

    public void clickOnSamsungGalaxyS6(){
        wait.until(ExpectedConditions.elementToBeClickable(samsungGalaxyS6Title)).click();
    }

    /**
     * Login lewat homepage (login dari halaman cart tidak menampilkan "Welcome ..."),
     * lalu kembali ke halaman tempat user berada sebelumnya.
     */
    public void logIn(String username, String password) {
        String currentUrl = driver.getCurrentUrl();
        goToHomePage();
        clickLogInMenuButton();
        inputUsername(username);
        inputPassword(password);
        clickFinalLogInButton();
        getWelcomeMessage();
        if (!driver.getCurrentUrl().equals(currentUrl)) {
            driver.get(currentUrl);
            getWelcomeMessage();
        }
    }
}
