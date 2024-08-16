package pages;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderCreationTest {
    private WebDriver driver;
    private MainPage mainPage;
    private WhoIsScooterPage whoIsScooterPage;
    private AboutRentPage aboutRentPage;

    private String name;
    private String surname;
    private String address;
    private int indexStantion;
    private String phone;
    private String date;
    private String howManyDays;
    private String color;
    private String comment;
    private boolean isHeader;

    public OrderCreationTest(
            String name, String surname, String address, int indexStantion, String phone, String date, String howManyDays, String color, String comment, boolean isHeader) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.indexStantion = indexStantion;
        this.phone = phone;
        this.date = date;
        this.howManyDays = howManyDays;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {"ИмяОдин", "Фамилия", "Адрес 1", 3, "79991111111", "18.08.2024", "сутки", "чёрный жемчуг", "комментарий ", true},
                {"ИмяДва", "Фамилиядва", "Адрес 2", 7, "+7888888888", "18.08.2024", "двое суток", "чёрный жемчуг", "комментарий ", false}
        };
    }
    @Rule
    public DriverRule factory = new DriverRule();


    @Test
    public void testCreateOrderWithUpButton() {
        WebDriver driver = factory.getDriver();
        try {
            mainPage = new MainPage(driver);
            whoIsScooterPage = new WhoIsScooterPage(driver);
            aboutRentPage = new AboutRentPage(driver);
            mainPage.open();
        mainPage.waitForLoadMainPage();
        if (isHeader) {
            mainPage.clickOrderButtonHeader();
        } else {
            mainPage.clickOrderButtonBody();
        }
        whoIsScooterPage.waitLoad()
                .setName(this.name)
                .setSurname(this.surname)
                .setAddress(this.address)
                .selectMetroStantion(this.indexStantion)
                .setPhone(this.phone)
                .clickButtonNext();
        aboutRentPage.waitLoAdbout()
                .setDate(this.date)
                .selectrentalPeriod(this.howManyDays)
                .setColor(this.color)
                .setComment(this.comment)
                .clickbuttonCreateOrder();
        assertTrue(aboutRentPage.orderIsProcessedTextIsDisplayed()); //окно не появится тест упадет
        } finally {
            if (driver != null) {
                try {
                    driver.close();  // Закрыть текущее окно
                    driver.quit();   // Завершить работу WebDriver в задании было обязательно закрыть окно
                } catch (Exception e) {
                    System.out.println("Ошибка при закрытии браузера: " + e.getMessage());
                }
            }
        }
    }
}
