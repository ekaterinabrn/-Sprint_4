package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

    public class MainPage {

        private WebDriver driver;
        private WebDriverWait wait;
        private final By homeHeader = By.className("Home_Header__iJKdX");
        // Локаторы вопросов аккордеона
        private By question1Locator = By.xpath("//div[@id='accordion__heading-0']");
        private final By questionsHeader = By.className("Home_FourPart__1uthg");
        // Локатор кнопки с принятием Cookies
        private final By acceptCookie = By.id("rcc-confirm-button");
        private final By topOrderButton = By.xpath(".//button[@class='Button_Button__ra12g' and text()='Заказать']");
        //локатор нижней кнопки создания заказа
        private final By orderButtonBody = By.xpath(".//button[@class='Button_Button__ra12g Button_UltraBig__UU3Lp']");

        // Локаторы ответов аккордеона
        private By answer1Locator = By.xpath("//div[@aria-labelledby='accordion__heading-0']");

        public MainPage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Увеличенное время ожидания
        }

        // Метод прокрутки к блоку "Вопросы о важном"
        public MainPage scrollToQuestions() {
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(questionsHeader));
            return this;
        }

        public MainPage acceptCookie() {
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(acceptCookie));
            cookieButton.click();
            return this;
        }

        // Метод для клика по вопросу
        public MainPage clickQuestion(int questionNumber) {
            By questionLocator = By.xpath(String.format("//div[@id='accordion__heading-%d']", questionNumber - 1));
            WebElement question = wait.until(ExpectedConditions.elementToBeClickable(questionLocator));
            question.click();
            return this;
        }

       // Метод для получения текста ответа
        public String getAnswerText(int questionNumber) {
            By answerLocator = By.xpath(String.format("//div[@aria-labelledby='accordion__heading-%d']", questionNumber - 1));
            WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
            return answer.getText();
        }

        // Метод для получения текста вопроса
        public String getQuestionText(int questionNumber) {
            By questionLocator = By.xpath(String.format("//div[@id='accordion__heading-%d']", questionNumber - 1));
            WebElement question = wait.until(ExpectedConditions.visibilityOfElementLocated(questionLocator));
            return question.getText();
        }
        //нажатие на верхнюю заказа в шапке
        public MainPage clickOrderButtonHeader() {
            WebElement tooButton = wait.until(ExpectedConditions.elementToBeClickable(topOrderButton));
            tooButton.click();
            return this;
        }
        // Метод прокрутки к нижней кнопке создания заказа
        public MainPage scrollToButtonBody() {
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(orderButtonBody));
            return this;
        }

        //нажатие на кнопку заказа нижнюю
        public MainPage clickOrderButtonBody() {
            WebElement ButtonBody = wait.until(ExpectedConditions.elementToBeClickable(orderButtonBody));
            ButtonBody.click();
            return this;
        }

        //метод ожидания загрузки главной страницы

        public MainPage waitForLoadMainPage() {

            WebElement divEl= wait.until(ExpectedConditions.visibilityOfElementLocated(homeHeader)); // Ожидание видимости элемента homeHeader

            return this;
        }
    }

