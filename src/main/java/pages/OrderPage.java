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

    private final By nameField = By.xpath(".//input[@placeholder ='* Имя']");
    // <input type="text" class="Input_Input__1iN_Z Input_Error__1Tx5d Input_Responsible__1jDKN" placeholder="* Имя" value="">
    private final By secondNameField = By.xpath(".//input[@placeholder ='* Фамилия']");

    private final By addressField = By.xpath(".//input[@placeholder ='* Адрес: куда привезти заказ']");

    private final By metroStationField = By.xpath(".//input[@placeholder ='* Станция метро']");
    private final By phoneNumberField = By.xpath(".//input[@placeholder ='* Телефон: на него позвонит курьер']");
    private final By continueButton = By.cssSelector(".Button_Middle__1CSJM");

    private final By whenToDeliver = By.xpath(".//input[@placeholder ='* Когда привезти самокат']");
    private final By rentalPeriod = By.cssSelector(".Dropdown-root");
    private final By rentalPeriodMenu = By.cssSelector(".Dropdown-menu");

    private final By scooterColor = By.className("Checkbox_Label__3wxSf");
    private final By commentForCourier = By.xpath(".//input[@placeholder ='Комментарий для курьера']");
    private final By orderButton = By.cssSelector("button.Button_Middle__1CSJM:nth-child(2)");

    private final By placingOrderWindow = By.className("Order_Modal__YZ-d3");

    private final By placingOrderButton = By.cssSelector("div.Order_Buttons__1xGrp:nth-child(2) > button:nth-child(2)");

    private final By showOrderButton = By.xpath(".//button[text() = 'Посмотреть статус']");

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
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(findElement(placingOrderWindow)));
    }

    public void clickPlacingOrderButton() {
        findElement(placingOrderButton).click();
    }

    public void waitForLoadShowOrderButton() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(findElement(showOrderButton)));
    }
}