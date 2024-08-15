package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WhoIsScooterPage {
    private final WebDriver driver;
    private WebDriverWait wait;
    //локатор открытия формы оформления заказа
    private final By whoIsScooterPage = By.xpath(".//div[starts-with(@class, 'Order_Form')]");
    //локатор поля имя
    private final By firstNameField = By.xpath(".//input[@placeholder = '* Имя']");
    //локатор поля фамилия
    private final By secondNameField = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Фамилия')]");
    //локатор поля адрес
    private final By adresField = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    //локатор поля станция метро
    private final By metroStantionField = By.xpath(".//input[@placeholder = '* Станция метро']");
    //локатор поля телефон
    private final By phoneField = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");
    //локатор кнопки далее
    private final By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //конструктор класса
    public WhoIsScooterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    //доступна ли форма для кого самокат

    public WhoIsScooterPage waitLoad() {
        WebElement divElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[starts-with(@class, 'Order_Form')]")));
        return this;
    }

    //метод заполняет поле имя
    public WhoIsScooterPage setName(String name) {
        driver.findElement(firstNameField).sendKeys(name);
        return this;
    }

    // метод заполняет поле фамилия
    public WhoIsScooterPage setSurname(String surname) {
        WebElement surnameFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(secondNameField));
        surnameFieldElement.sendKeys(surname);
        return this;
    }

    //метод заполняет поле адрес
    public WhoIsScooterPage setAddress(String address) {
        WebElement addressFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(adresField));
        addressFieldElement.sendKeys(address);
        return this;
    }

    //метод заполняет поле  станция метро
    public WhoIsScooterPage selectMetroStantion(int indexStantion) {
        //клик по полю станции
        WebElement metroStantionselect = wait.until(ExpectedConditions.elementToBeClickable(metroStantionField));
        metroStantionselect.click();
        //выборстанции
        By metrostantionLocator = By.cssSelector(String.format("[data-index='%d']", indexStantion));
        WebElement metrostantion =
                wait.until(ExpectedConditions.elementToBeClickable(metrostantionLocator));
        metrostantion.click();
        return this;
    }

    //метод заполняет поле телефон
    public WhoIsScooterPage setPhone(String phone) {
        WebElement phoneFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneField));
        phoneFieldElement.sendKeys(phone);
        return this;

    }
    public WhoIsScooterPage clickButtonNext() {
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(buttonNext));
        nextButton.click();
        return this;
    }
}
