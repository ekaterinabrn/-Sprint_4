package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderCreationPage {
    private WebDriver driver;
    // локатор верхней кнопки создания заказа
    private final By topOrderButton = By.xpath(".//button[@class='Button_Button__ra12g' and text()='Заказать']";
    //локатор нижней кнопки создания заказа
    private final By bottomOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_UltraBig__UU3Lp']");
    //локатор открытия формы оформления заказа
    private final By OrderformForWhomIsScooter = By.xpath(".//div[starts-with(@class, 'Order_Form')]");
    //локатор поля имя
    private final By firstName =By.xpath(".//input[@placeholder = '* Имя']");
    //локатор поля фамилия
    private final By secondName = By.xpath(".//div[starts-with(@class, 'Order_Form')]//input[contains(@placeholder,'Фамилия')]");
    //локатор поля адрес
    private final By adres = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    //локатор поля станция метро
    private final By metroStantion = By.xpath(".//input[@placeholder = '* Станция метро']");
    //локатор поля телефон
    private final By phone = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");
    //локатор кнопки далее
    private final By buttonNext = By.className("Button_Button__ra12g Button_Middle__1CSJM");
    //локатор поля когда привезти
    private final By date = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");
    //локатор поля срок аренды
    private final By rentalPeriod = By.className("Dropdown-placeholder");
    //локатор  выбор цвета черный
    private final By colorBlack  = By.className("Checkbox_Input__14A2w");
    // локатор поля комментарий
    private final By comment = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");
    //локатор кнопки заказать для непосредственного оформления заказа
    private final By buttonCreateOrder =By.xpath(".//div[@class = 'Order_Buttons__1xGrp']/button[text()='Заказать']");
//локатор кнопки да в о поп апе оформления заказа
    private final By buttonYes = By.xpath(".//button[text() = 'Да']");
// локатор окна заказ оформлен
    private final By orderCreated =By.xpath(".//*[text() = 'Заказ оформлен']");
   //конструктор класса
    public OrderCreationPage(WebDriver driver){
        this.driver = driver;}
    // метод проверяет, активна ли верхняя кнопка заказать
    public boolean topOrderButtonIsEnabled() {
        return driver.findElement(topOrderButton).isEnabled();}
    // метод кликает по верхняя кнопка заказать
    public void clicktopOrderButton() {
        driver.findElement(topOrderButton).click();
    }
    //роверяем активна ли нижняя кнопка создания заказа
    public boolean bottomOrderButtonIsEnabled() {
        return driver.findElement(bottomOrderButton).isEnabled();}
    //метод кликает по нижней кнопке создания заказа
    public  void clickbottomOrderButton() {
        driver.findElement(bottomOrderButton).click();
    }
    //ожидание открытия формы заказа
    public void waitForLoadForm() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
    .until(ExpectedConditions.visibilityOf(this.driver.findElement(this.OrderformForWhomIsScooter)));
    }
//метод заполняет поле имя
public void setfirstName(String userName) {

    driver.findElement(firstName).sendKeys(userName);
    }
    //метод заполняет поле фамилия
public void setSecondName(String userSecondName){
        driver.findElement(secondName).sendKeys(userSecondName);
}
  //метод заполняет поле адрес
  public void setAdres (String userAdres){
        driver.findElement(adres).sendKeys(userAdres);
  }
    //метод заполняет поле  станция метро
public void setMetroStantion() {};
    //метод заполняет поле телефон
public void setPhone (String userPhone){
        driver.findElement(phone).sendKeys(userPhone);
}
//
public void clickbuttonNext() {
    driver.findElement(this.buttonNext).click();
}
//
public void OrderformForWhomIsScooter
(String userName, String userSecondName, String userAdres, String userPhone  ) {
    setfirstName(userName);
    setSecondName(userSecondName);
    setAdres (userAdres);
    setMetroStantion();
    setPhone (userPhone);
        clickbuttonNext();
}
}
