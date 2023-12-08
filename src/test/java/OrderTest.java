import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.OrderPage;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;

    private final boolean orderByButtonInHeader;
    private final String name;
    private final String secondName;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String quantity;
    private final String color;
    private final String comment;


    public OrderTest(String buttonLocation,
                     String name,
                     String secondName,
                     String address,
                     String metroStation,
                     String phoneNumber,
                     String date,
                     String quantity,
                     String color,
                     String comment)
    {
        if (buttonLocation.equals("headerButton")) {
            this.orderByButtonInHeader = true;
        } else this.orderByButtonInHeader = !buttonLocation.equals("bodyButton");

        this.name = name;
        this.secondName = secondName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.quantity = quantity;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {
                    "headerButton",
                    "Иван",
                    "Иванов",
                    "ул.Ивановская",
                    "Чкаловская",
                    "89999999999",
                    "21.11.2023",
                    "пятеро суток",
                    "чёрный жемчуг",
                    "позвонить перед доставкой"
                },
                {
                    "bodyButton",
                    "Федор",
                    "Волков",
                    "ул.Ленина",
                    "Динамо",
                    "84569052300",
                    "08.02.2024",
                    "двое суток",
                    "серая безысходность",
                    "напомнить за пару дней"
                },
        };
    }

    @org.junit.Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void testOrder() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        mainPage.open();
        if (orderByButtonInHeader) {
            mainPage.clickOrderInHeaderButton();
        }
        else {
            mainPage.clickOrderInBodyButton();
        }

        orderPage.waitForLoadFirstPart();

        orderPage.setNameField(name);
        orderPage.setSecondNameField(secondName);
        orderPage.setAddressField(address);
        orderPage.setMetroStationField(metroStation);
        orderPage.setPhoneNumberField(phoneNumber);

        orderPage.clickContinueButton();

        orderPage.waitForLoadSecondPart();

        orderPage.setWhenToDeliverField(date);
        orderPage.setRentalPeriodField(quantity);
        orderPage.setScooterColorField(color);
        orderPage.setCommentForCourierField(comment);

        orderPage.clickOrderButton();

        orderPage.waitFoLoadPlacingModalWindow();
        orderPage.clickPlacingOrderButton();

        orderPage.waitForLoadShowOrderButton();
    }

    @org.junit.After
    public void tearDown() {
        driver.quit();
    }
}