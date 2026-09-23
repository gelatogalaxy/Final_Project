package WebUIPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePagePOM {
    WebDriver driver;
    By logInMenuButton = By.id("login2");
    By finalLogInButton = By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]");
    By usernameInputText = By.id("loginusername");
    By passwordInputText = By.id("loginpassword");
    By welcomeMessage = By.id("nameofuser");
    By samsungGalaxyS6Title = By.xpath("//*[@id=\"tbodyid\"]/div[1]/div/div/h4/a");

    public HomePagePOM(WebDriver driver) {
        this.driver = driver;
    }

    public void goToHomePage(){
        driver.get("https://www.demoblaze.com/");
    }

    public void clickLogInMenuButton(){
        driver.findElement(logInMenuButton).click();
    }

    public void inputUsername(String username){
        driver.findElement(usernameInputText).sendKeys(username);
    }

    public void inputPassword(String password) {
        driver.findElement(passwordInputText).sendKeys(password);
    }

    public void clickFinalLogInButton(){
        driver.findElement(finalLogInButton).click();
    }
    public String getWelcomeMessage(){
        return driver.findElement(welcomeMessage).getText();
    }
    public String getAlertMessage(){
        return driver.switchTo().alert().getText();
    }
    public void clickOnSamsungGalaxyS6(){
        driver.findElement(samsungGalaxyS6Title).click();
    }

}
