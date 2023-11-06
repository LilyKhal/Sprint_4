package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class MainPage {
    private final WebDriver driver;
    public static final String URL = "https://qa-scooter.praktikum-services.ru";

    private final By blockQuestions = By.xpath(".//div[@class = 'Home_SubHeader__zwi_E' and text() = 'Вопросы о важном']");

    private final By orderInHeaderButton = By.xpath("/html/body/div/div/div[1]/div[1]/div[2]/button[1]");

    private final By orderInBodyButton = By.xpath("/html/body/div/div/div/div[4]/div[2]/div[5]/button");
    private WebElement findElement(By locator) {
        return driver.findElement(locator);
    }
    private By getAccordionQuestionByIndex(int index) {
        String path = ".//div[@id = 'accordion__heading-" + index + "']";
        return By.xpath(path);
    }

    private By getAccordionAnswerByIndex(int index) {
        String path = ".//div[@id = 'accordion__panel-" + index + "']";
        return By.xpath(path);
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
    }

    public void scrollToBlockQuestions(){
        WebElement element = findElement(blockQuestions);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickAccordionQuestionButton(int index){
        findElement(getAccordionQuestionByIndex(index)).click();
    }

    public String getAccordionAnswer(int index) {
        return findElement(getAccordionAnswerByIndex(index)).getText();
    }

    public String getAccordionQuestion(int index) {
        return findElement(getAccordionQuestionByIndex(index)).getText();
    }

    public void clickOrderInHeaderButton() {
        findElement(orderInHeaderButton).click();
    }

    public void clickOrderInBodyButton(){
        WebElement element = findElement(orderInBodyButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        element.click();
    }
}
