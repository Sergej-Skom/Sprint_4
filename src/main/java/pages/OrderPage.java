package pages;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.containsString;

public class OrderPage {
    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Заполнение поля ввода имени
    public OrderPage inputFirstName(String firstName) {
        driver.findElement(By.xpath("//input[@placeholder= '* Имя']")).sendKeys(firstName);
        return this;
    }

    // Заполнение поля ввода фамилии
    public OrderPage inputSecondName(String secondName) {
        driver.findElement(By.xpath("//input[@placeholder= '* Фамилия']")).sendKeys(secondName);
        return this;
    }

    // Заполнение поля ввода адреса
    public OrderPage inputAddress(String address) {
        driver.findElement(By.xpath("//input[@placeholder= '* Адрес: куда привезти заказ']")).sendKeys(address);
        return this;
    }

    // Клик по полю выбора метро
    public void clickMetro() {
        driver.findElement(By.className("select-search")).click();
    }

    // Ввод названия станции и выбор первой станции из списка
    public void chooseMetroStation(String metroStation) {
        driver.findElement(By.className("select-search__input")).sendKeys(metroStation);
        driver.findElement(By.className("select-search__select")).click();
    }

    // Заполнение поля ввода номера телефона
    public OrderPage inputPhoneNumber(String phoneNumber) {
        driver.findElement(By.xpath("//input[@placeholder= '* Телефон: на него позвонит курьер']")).sendKeys(phoneNumber);
        return this;
    }

    // Кликнуть по кнопке "Далее"
    public void clickNext() {
        driver.findElement(By.xpath("//button[@class= 'Button_Button__ra12g Button_Middle__1CSJM']")).click();
    }

    // Переход на экран "Про аренду"
    // Кликнуть по полю выбора даты, установить нужную дату и нажать Enter
    public OrderPage inputDate(String date) {
        driver.findElement(By.xpath("//input[@placeholder= '* Когда привезти самокат']")).sendKeys(date);
        driver.findElement(By.xpath("//input[@placeholder= '* Когда привезти самокат']")).sendKeys(Keys.ENTER);
        return this;
    }

    // Кликнуть по выбору срока аренды и выбрать первый срок из выпадающего меню
    public void clickTimeRent() {
        driver.findElement(By.className("Dropdown-placeholder")).click();
        driver.findElement(By.className("Dropdown-option")).click();
    }

    // Выбор первого чекбокса для цвета самоката
    public void clickColorScooter(String scooterColor) {
        driver.findElement(By.id(scooterColor)).click();
    }

    // Заполнение поля "Комментарий для курьера"
    public OrderPage inputCommentForCourier(String commentForCourier) {
        driver.findElement(By.xpath("//input[@placeholder= 'Комментарий для курьера']")).sendKeys(commentForCourier);
        return this;
    }

    // Кликнуть по кнопке "Заказать"
    public void clickOrder() {
        driver.findElement(By.xpath("//button[@class= 'Button_Button__ra12g Button_Middle__1CSJM']")).click();
    }

    // Проверка появления окна подтверждения заказа
    public void checkOrderVerificationWindow() {
        MatcherAssert.assertThat("Не появилось окно подтверждения заказа",
                driver.findElement(By.className("Order_ModalHeader__3FDaJ")).getText(),
                containsString("Хотите оформить заказ?"));
    }

    // Клик по кнопке "Да"
    public void clickYesOrder() {
        driver.findElement(By.xpath("//button[text()='Да']")).click();
    }

    // Считывание текста в окне подтверждения заказа
    public String getFinalOrderMessage(){
        return driver.findElement(By.className("Order_ModalHeader__3FDaJ")).getText();
    }
}