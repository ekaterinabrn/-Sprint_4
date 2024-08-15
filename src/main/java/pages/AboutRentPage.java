package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AboutRentPage {
    private final WebDriver driver;
    private WebDriverWait wait;
    //локатор появления второй части формы
    private final By aboutRent = By.className("Order_Form__17u6u");
    //локатор поля когда привезти
    private final By dateField = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");
    //Выбранная в календаре дата
    private final By dateSelected = By.className("react-datepicker__day--selected");
    //локатор поля срок аренды
    private final By rentalPeriodField = By.xpath("//span[@class='Dropdown-arrow']");
    //локатор поля выбора срока аренды из выпадающего списка
    private final By termDropdownOption = By.className("Dropdown-menu");
    // Локаторы для чекбоксов черный и серый
    private final By blackCheckbox = By.cssSelector("input#black");
    private final By greyCheckbox = By.cssSelector("input#grey");
    // локатор поля комментарий
    private final By commentField = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");
    //локатор кнопки заказать для непосредственного оформления заказа
    private final By buttonCreateOrder =By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    //Локатор кнопки согласия на оформление заказа
    private final By yesOrderButtonField = By.xpath("//button[text() = 'Да']");
    //Локатор для окна с подтверждением создания заказа
    private final By orderCreateField = By.xpath("//*[text() = 'Заказ оформлен']");


    //конструктор класса
    public AboutRentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    //метод ожидания загрузки страницы
    public AboutRentPage  waitWhoIsScooterPage() {
        new WebDriverWait(this.driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(this.driver.findElement(this.aboutRent)));
        return this;
    }
    //попытка ожидания загрузки
    public AboutRentPage waitLoAdbout(){
        WebElement divElement = wait.until(ExpectedConditions.visibilityOfElementLocated(aboutRent));
        return this;}

    //выбрали дату
    public AboutRentPage setDate(String date) {
        WebElement dataFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(dateField));
        dataFieldElement.sendKeys(date);
        return this;
    }
    //срок аренды

    public AboutRentPage selectrentalPeriod(String howManyDays) {
        // Клик на поле выбора срока аренды
        WebElement dropdownField = wait.until(ExpectedConditions.visibilityOfElementLocated(rentalPeriodField));
        dropdownField.click();
        // Формирование селектора для выбора нужного срока аренды
        By howManyDaysLocator  = By.xpath(String.format("//div[@class='Dropdown-option' and text()='%s']", howManyDays));
        WebElement rentalPerid = wait.until(ExpectedConditions.elementToBeClickable(howManyDaysLocator));
        rentalPerid.click();
        return this;
    }

    //выбор цвета
    public AboutRentPage setColor(String color) {
        By colorLocator;
        switch (color.toLowerCase()) {
            case "чёрный жемчуг":
                colorLocator = blackCheckbox;
                break;
            case "серая безысходность":
                colorLocator = greyCheckbox;
                break;
            default:
                throw new IllegalArgumentException("Неизвестный цвет: " + color);
        }

        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(colorLocator));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        return this;
    }
    //заполнение поля комментарий
    public AboutRentPage setComment(String comment) {
        WebElement setcommentField = wait.until(ExpectedConditions.visibilityOfElementLocated(commentField));
        setcommentField.sendKeys(comment);
        return this;
    }
    //нажать кнопку оформить заказ
    public AboutRentPage clickbuttonCreateOrder() {
        this.driver.findElement(this.buttonCreateOrder).click();
        return this;
    }


}
