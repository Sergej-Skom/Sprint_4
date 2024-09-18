import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainPage;
import pages.OrderPage;

import static org.hamcrest.CoreMatchers.containsString;


//Параметризированный тест
@RunWith(Parameterized.class)
public class checkOrderSamokat {

    private WebDriver driver;

    @Before
    public void startUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    private final int n;
    private final String firstName;
    private final String secondName;
    private final String adress;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String samokatColor;
    private final String commentForCourier;


    public checkOrderSamokat(int n, String firstName, String secondName, String adress, String metroStation, String phoneNumber, String date, String samokatColor, String commentForCourier) {
        this.n = n;
        this.firstName = firstName;
        this.secondName = secondName;
        this.adress = adress;

        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.samokatColor = samokatColor;
        this.commentForCourier = commentForCourier;
    }


    @Parameterized.Parameters
    public static Object[][] getCredentials() {

        return new Object[][]{
                {1, "Олег", "Иванов", "ул. Садовая, д.22", "Сокольники", "+75544231212", "19.09.2024", "black", "Динамик не работает, пишите смс"},
                {2, "Пётр", "Иващенко", "ул. Ленина, д.11", "Динамо", "+78899231212", "25.09.2024", "grey", "Динамик не работает, пишите сообщение"},
        };
    }

    @Test
    public void positiveCheckOrderSamokat() {
        // Создание объекта главной страницы
        MainPage mainPage = new MainPage(driver);
        // Открытие страницы тестового приложени
        mainPage.openMainPage();
        // Клик на кнопку "Заказать"
        mainPage.clickOrder(n);
        // Создание объекта страницы "Заказать"
        OrderPage orderPage = new OrderPage(driver);
        // Заполнение поля ввода имени
        orderPage.inputFirstName(firstName);
        // Заполнение поля ввода фамилии
        orderPage.inputSecondName(secondName);
        // Заполнение поля ввода адреса
        orderPage.inputAddress(adress);
        // Клик по полю выбора метро
        orderPage.clickMetro();
        // Ввод названия станции и выбор первой станции из списка
        orderPage.chooseMetroStation(metroStation);
        // Заполнение поля ввода номера телефона
        orderPage.inputPhoneNumber(phoneNumber);
        // Кликнуть по кнопке "Далее"
        orderPage.clickNext();
        // Переход на экран "Про аренду"
        // Кликнуть по полю выбора даты, установить нужную дату и нажать Enter
        orderPage.inputDate(date);
        // Кликнуть по выбору срока аренды и выбрать первый срок из выпадающего меню
        orderPage.clickTimeRent();
        // Выбор первого чекбокса для цвета самоката
        orderPage.clickColorScooter(samokatColor);
        // Заполнение поля "Комментарий для курьера"
        orderPage.inputCommentForCourier(commentForCourier);
        // Кликнуть по кнопке "Заказать"
        orderPage.clickOrder();
        //Проверка появления окна подтверждения заказа
        orderPage.checkOrderVerificationWindow();
        // Клик по кнопке "Да"
        orderPage.clickYesOrder();
        //Проверка содержимого финального окна подтверждения заказа
        MatcherAssert.assertThat("Не появилось окно (Заказ оформлен)",
                orderPage.getFinalOrderMessage(), containsString("Заказ оформлен"));
    }

    @After
    public void tearDown() {
        // Закрыли браузер
        driver.quit();
    }

}
