import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.DriverRule;
import pages.MainPage;


@RunWith(Parameterized.class)
    public class FAQtest {

    private WebDriver driver;
    private MainPage mainPage;
    private String question;
    private String expectedAnswer;
    private int questionNumber;

    public FAQtest(String question, String expectedAnswer, int questionNumber) {
        this.question = question;
        this.expectedAnswer = expectedAnswer;
        this.questionNumber = questionNumber;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 1},
                {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", 2},
                {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", 3},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", 4},
                {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", 5},
                {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", 6},
                {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", 7},
                {"Я живу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области.", 8}


        };
    }

    @Rule
    public DriverRule factory = new DriverRule();
    @Rule
    public ErrorCollector collector = new ErrorCollector();


    @Test
    public void testAccordionAnswers() {
        WebDriver driver = factory.getDriver();
        mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.acceptCookie();
        mainPage.scrollToQuestions();
        mainPage.scrollToQuestions();
        mainPage.clickQuestion(questionNumber);
        // Получить текст вопроса и ответа
        String actualQuestion = mainPage.getQuestionText(questionNumber);
        String actualAnswer = mainPage.getAnswerText(questionNumber);

        //  ErrorCollector для сбора ошибок
        collector.checkThat("Текст вопроса не совпадает!", actualQuestion, CoreMatchers.is(question));
        collector.checkThat("Текст ответа не совпадает!", actualAnswer, CoreMatchers.is(expectedAnswer));
    }

}


