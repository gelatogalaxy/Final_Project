package WebUIPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePagePOM extends BasePage {
    By logInMenuButton = By.id("login2");
    By finalLogInButton = By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]");
    By usernameInputText = By.id("loginusername");
    By passwordInputText = By.id("loginpassword");
    By welcomeMessage = By.id("nameofuser");
    By samsungGalaxyS6Title = By.linkText("Samsung galaxy s6");

    public HomePagePOM(WebDriver driver) {
        super(driver);
    }

    public void goToHomePage(){
        openUrl("https://www.demoblaze.com/");
    }

    public void clickLogInMenuButton(){
        click(logInMenuButton);
    }

    public void inputUsername(String username){
        type(usernameInputText, username);
    }

    public void inputPassword(String password) {
        type(passwordInputText, password);
    }

    public void clickFinalLogInButton(){
        click(finalLogInButton);
    }

    public String getWelcomeMessage(){
        // Teks "Welcome ..." baru muncul setelah request login selesai
        wait.until(ExpectedConditions.textToBePresentInElementLocated(welcomeMessage, "Welcome"));
        return getText(welcomeMessage);
    }

    public String getAlertMessage(){
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }

    public void clickOnSamsungGalaxyS6(){
        click(samsungGalaxyS6Title);
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
