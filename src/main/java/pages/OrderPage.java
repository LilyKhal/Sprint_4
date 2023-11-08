package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class OrderPage {
    private final WebDriver driver;
    public static final String URL = "https://qa-scooter.praktikum-services.ru/order";

    private final By nameField = By.xpath("/html/body/div/div/div[2]/div[2]/div[1]/input");
    private final By secondNameField = By.xpath("/html/body/div/div/div[2]/div[2]/div[2]/input");

    private final By addressField = By.xpath("/html/body/div/div/div[2]/div[2]/div[3]/input");

    private final By metroStationField = By.xpath("/html/body/div/div/div[2]/div[2]/div[4]/div[1]/div/input");
    private final By phoneNumberField = By.xpath("/html/body/div/div/div[2]/div[2]/div[5]/input");
    private final By continueButton = By.xpath("/html/body/div/div/div[2]/div[3]/button");
    private final By whenToDeliver = By.xpath("/html/body/div/div/div[2]/div[2]/div[1]/div[1]/div/input");
    private final By rentalPeriod = By.cssSelector(".Dropdown-root");
    private final By rentalPeriodMenu = By.cssSelector(".Dropdown-menu");

    private final By scooterColor = By.className("Checkbox_Label__3wxSf");
    private final  By commentForCourier = By.xpath("/html/body/div/div/div[2]/div[2]/div[4]/input");
    private final By orderButton = By.xpath("/html/body/div/div/div[2]/div[3]/button[2]");

    private final By placingOrderWindow = By.xpath("/html/body/div/div/div[2]/div[5]");

    private final By placingOrderButton = By.cssSelector("div.Order_Buttons__1xGrp:nth-child(2) > button:nth-child(2)");

    private final By confirmationOrderWindow = By.xpath("/html/body/div/div/div[2]/div[5]");
    WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    public void setNameField(String name) {
        findElement(nameField).sendKeys(name);
    }
    public void setSecondNameField(String secondName){
        findElement(secondNameField).sendKeys(secondName);
    }
    public void setAddressField(String address){
        findElement(addressField).sendKeys(address);
    }
    public void setPhoneNumberField(String phoneNumber){
        findElement(phoneNumberField).sendKeys(phoneNumber);
    }
    public void setMetroStationField(String metroStation){
        findElement(metroStationField).sendKeys(metroStation);
        findElement(metroStationField).sendKeys(Keys.DOWN, Keys.RETURN);
    }
    public void clickContinueButton(){
        findElement(continueButton).sendKeys(Keys.ENTER);
    }

    public void waitForLoadFirstPart() {
        List<WebElement> elements =  Arrays.asList(
                findElement(nameField),
                findElement(secondNameField),
                findElement(addressField),
                findElement(metroStationField),
                findElement(phoneNumberField));

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForLoadSecondPart() {
        List<WebElement> elements =  Arrays.asList(
                findElement(whenToDeliver),
                findElement(rentalPeriod),
                findElement(scooterColor),
                findElement(commentForCourier));

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void setWhenToDeliverField(String date){
          findElement(whenToDeliver).sendKeys(date);
          findElement(whenToDeliver).sendKeys(Keys.ENTER);
    }
    public void setRentalPeriodField(String quantity) {
        findElement(rentalPeriod).click();
        WebElement menu = findElement(rentalPeriodMenu);
        List<WebElement> options = menu.findElements(By.xpath("./child::*"));
        for (WebElement option : options) {
            if (option.getText().equals(quantity)) {
                option.click();
                return;
            }
        }
    }
    public void setScooterColorField(String color){
        List<WebElement> elements = driver.findElements(scooterColor);
        for (WebElement element : elements) {
            if (element.getText().equals(color)) {
                element.click();
                return;
            }
        }
    }
    public void setCommentForCourierField(String comment){
        findElement(commentForCourier).sendKeys(comment);
    }
    public void clickOrderButton(){
        findElement(orderButton).sendKeys(Keys.ENTER);
    }

    public void waitFoLoadPlacingModalWindow() {
        List<WebElement> elements =  Arrays.asList(
                findElement(whenToDeliver),
                findElement(rentalPeriod),
                findElement(scooterColor),
                findElement(commentForCourier));

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(findElement(placingOrderWindow)));
    }

    public void clickPlacingOrderButton(){
        findElement(placingOrderButton).click();
    }
    public boolean confirmationOrderWindow(){
        return findElement(confirmationOrderWindow).isDisplayed();
    }
}